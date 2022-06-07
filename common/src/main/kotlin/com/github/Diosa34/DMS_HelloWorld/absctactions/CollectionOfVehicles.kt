package com.github.Diosa34.DMS_HelloWorld.absctactions

import com.github.Diosa34.DMS_HelloWorld.collection.Groups
import com.github.Diosa34.DMS_HelloWorld.collection.Vehicle
import com.github.Diosa34.DMS_HelloWorld.collection.VehicleType
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.datetime.Instant

interface CollectionOfVehicles: Iterable<Vehicle> {
    fun add(vehicle: Vehicle, user: User): Int

    fun addIfMin(name: String, vehicle: Vehicle, user: User): Pair<AddIfMinResult, Int?>

    fun clear(user: User): ClearResult

    fun countByType(type: VehicleType): Int

    fun groupCountingByType(): Groups

    fun info(): Information

    fun removeById(id: Int, user: User): RemoveByIdResult

    fun removeFirst(user: User): Boolean

    fun removeLower(name: String, user: User): RemoveLowerResult

    override fun iterator(): Iterator<Vehicle>

    fun sumOfEnginePower(): Float

    fun update(id: Int, vehicle: Vehicle, user: User): UpdateResult

    enum class AddIfMinResult(
        val isSuccess: Boolean,
    ) {
        EMPTY(true),
        SUCCESS(true),
        LESS_FOUND(false);
    }

    enum class ClearResult(
        val isSuccess: Boolean
    ){
        DELETED(true),
        NOT_FOUND(false)
    }

    enum class RemoveByIdResult(
        val isSuccess: Boolean
    ){
        EMPTY(false),
        DELETED(true),
        NOT_FOUND(false)
    }

    enum class RemoveLowerResult(
        val isSuccess: Boolean
    ){
        EMPTY(false),
        DELETED(true),
        LESS_NOT_FOUND(false)
    }

    enum class UpdateResult(
        val isSuccess: Boolean
    ){
        EMPTY(false),
        UPDATED(true),
        NOT_FOUND(false)
    }

    class Information(
        val elemCount: Int = 0,
        val initDate: Instant
    ) {
        val typeOfCollection = "Средства передвижения"
    }
}

