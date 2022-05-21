package com.github.Diosa34.DMS_HelloWorld

import java.time.LocalDateTime
import java.util.*


class CollectionInMemory: CollectionOfVehicles {

    private val collection: LinkedList<Vehicle> = LinkedList()
    private val initDate: LocalDateTime = LocalDateTime.now()
    private var maxId = 0

    override fun print(){
        println(this.collection)
    }
    /**
     * A new ID is created that is one greater than the largest existing one.
     */
    private fun setId(vehicle: Vehicle){
        vehicle.id = maxId + 1
    }

    private fun decreaseId(id: Int) {
        if (id == this.maxId) {
            this.maxId -= 1
        }
    }

    override fun add(vehicle: Vehicle) {
        if (vehicle.id == null) {
            setId(vehicle)
            this.maxId += 1
        } else if (vehicle.id > this.maxId) {
            this.maxId = vehicle.id
        }
        this.collection.add(vehicle)
    }

    override fun addIfMin(name: String, vehicle: Vehicle): CollectionOfVehicles.AddIfMinResult {
        return if (this.collection.size != 0){
            val minElem = this.collection.sortedWith(compareBy { it.name.length })[0]
            if (minElem.compareTo(name) > 0) {
                this.collection.add(vehicle)
                CollectionOfVehicles.AddIfMinResult.SUCCESS
            } else {
                CollectionOfVehicles.AddIfMinResult.LESS_FOUND
            }
        } else {
            this.collection.add(vehicle)
            CollectionOfVehicles.AddIfMinResult.EMPTY
        }
    }

    override fun clear() {
        this.collection.clear()
        maxId = 0
    }

    override fun countByType(type: VehicleType): Int {
        return this.collection.count {
            it.type == type
        }
    }

    override fun groupCountingByType(): Groups {
        return Groups(this.collection.count {
            it.type == VehicleType.CAR
        }, this.collection.count {it.type == VehicleType.SUBMARINE},
        this.collection.count {it.type == VehicleType.SHIP})
    }

    override fun info(): CollectionOfVehicles.Information {
        return CollectionOfVehicles.Information(this.collection.size, this.initDate)
    }

    override fun removeById(id: Int): CollectionOfVehicles.RemoveByIdResult {
        if (collection.size == 0) {
            return CollectionOfVehicles.RemoveByIdResult.EMPTY
        } else if (collection.removeIf {
                    it.id == id }) {
            decreaseId(id)
            return CollectionOfVehicles.RemoveByIdResult.DELETED
        }
        return CollectionOfVehicles.RemoveByIdResult.NOT_FOUND
    }

    override fun removeFirst(): Boolean {
        return if (this.collection.size > 0) {
            decreaseId(this.collection.first.id)
            this.collection.removeFirst()
            true
        } else {
            false
        }
    }

    override fun removeLower(name: String): CollectionOfVehicles.RemoveLowerResult {
        return if (this.collection.size != 0) {
            if (this.collection.any { it < name }) {
                this.collection.filter { elem ->
                    elem < name
                }.forEach { decreaseId(it.id)
                this.collection.remove(it)}
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

    override fun update(id: Int, vehicle: Vehicle): CollectionOfVehicles.UpdateResult {
        return if (this.collection.size > 0) {
            if (this.collection.none { it.id == id }) {
                CollectionOfVehicles.UpdateResult.NOT_FOUND
            } else {
                for (elem in collection) {
                    if (elem.id == id) {
                        vehicle.id = id
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