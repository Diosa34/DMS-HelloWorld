package com.github.Diosa34.DMS_HelloWorld.collection

import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.util.*


class CollectionInMemory : CollectionOfVehicles {

    private val collection: LinkedList<Vehicle> = LinkedList()
    private val initDate: Instant = Clock.System.now()

    fun copyFromDB(vehicle: Vehicle){
        this.collection.add(vehicle)
    }

    override fun add(vehicle: Vehicle, user: User): Int {
        this.collection.add(vehicle)
        return vehicle.id ?: throw NullPointerException()
    }

    override fun addIfMin(name: String, vehicle: Vehicle, user: User): Pair<CollectionOfVehicles.AddIfMinResult, Int?> {
        return if (this.collection.size != 0) {
            val minElem = this.collection.sortedWith(compareBy { it.name.length })[0]
            if (minElem.compareTo(name) > 0) {
                Pair(CollectionOfVehicles.AddIfMinResult.SUCCESS, add(vehicle, user))
            } else {
                Pair(CollectionOfVehicles.AddIfMinResult.LESS_FOUND, null)
            }
        } else {
            Pair(CollectionOfVehicles.AddIfMinResult.EMPTY, add(vehicle, user))
        }
    }

    override fun clear(user: User): CollectionOfVehicles.ClearResult {
        if (this.collection.any { it.username == user.login}) {
            this.collection.removeIf { elem ->
                elem.username == user.login
            }
            return CollectionOfVehicles.ClearResult.NOT_FOUND
        }
        return CollectionOfVehicles.ClearResult.DELETED
    }

    override fun countByType(type: VehicleType): Int {
        return this.collection.count {
            it.type == type
        }
    }

    override fun groupCountingByType(): Groups {
        return Groups(this.collection.count {
            it.type == VehicleType.CAR
        }, this.collection.count { it.type == VehicleType.SUBMARINE },
            this.collection.count { it.type == VehicleType.SHIP })
    }

    override fun info(): CollectionOfVehicles.Information {
        return CollectionOfVehicles.Information(this.collection.size, this.initDate)
    }

    override fun removeById(id: Int, user: User): CollectionOfVehicles.RemoveByIdResult {
        if (collection.size == 0) {
            return CollectionOfVehicles.RemoveByIdResult.EMPTY
        } else if (collection.removeIf {
                it.id == id && it.username == user.login
            }) {
            return CollectionOfVehicles.RemoveByIdResult.DELETED
        }
        return CollectionOfVehicles.RemoveByIdResult.NOT_FOUND
    }

    override fun removeFirst(user: User): Boolean {
        return if (this.collection.size > 0) {
            if (this.collection.first().username == user.login) {
                this.collection.removeFirst()
            }
            this.collection.removeFirst()
            true
        } else {
            false
        }
    }

    override fun removeLower(name: String, user: User): CollectionOfVehicles.RemoveLowerResult {
        return if (this.collection.size != 0) {
            if (this.collection.any { it.name < name && it.username == user.login}) {
                this.collection.removeIf { elem ->
                    elem.name < name && elem.username == user.login
                }
                CollectionOfVehicles.RemoveLowerResult.DELETED
            } else {
                CollectionOfVehicles.RemoveLowerResult.LESS_NOT_FOUND
            }
        } else {
            CollectionOfVehicles.RemoveLowerResult.EMPTY
        }
    }

    override fun iterator(): Iterator<Vehicle> {
        return this.collection.iterator()
    }

    override fun sumOfEnginePower(): Float {
        var summa = 0.0F
        for (elem in this.collection) {
            summa += elem.enginePower
        }
        return summa
    }

    override fun update(id: Int, vehicle: Vehicle, user: User): CollectionOfVehicles.UpdateResult {
        return if (this.collection.size > 0) {
            if (this.collection.none { it.id == id && it.username == user.login}) {
                CollectionOfVehicles.UpdateResult.NOT_FOUND
            } else {
                for (elem in collection) {
                    if (elem.id == id && elem.username == user.login) {
                        collection[collection.indexOf(elem)] = vehicle
                    }
                }
                CollectionOfVehicles.UpdateResult.UPDATED
            }
        } else {
            CollectionOfVehicles.UpdateResult.EMPTY
        }
    }
}