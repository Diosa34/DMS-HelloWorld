package com.github.Diosa34.DMS_HelloWorld.absctactions

import com.github.Diosa34.DMS_HelloWorld.collection.Groups
import com.github.Diosa34.DMS_HelloWorld.Vehicle
import com.github.Diosa34.DMS_HelloWorld.collection.VehicleType
import java.time.LocalDateTime

interface CollectionOfVehicles: Iterable<Vehicle> {
    fun print()
    fun add(vehicle: Vehicle)

    fun addIfMin(name: String, vehicle: Vehicle): AddIfMinResult

    fun clear()

    fun countByType(type: VehicleType): Int

    fun groupCountingByType(): Groups

    fun info(): Information

    fun removeById(id: Int): RemoveByIdResult

    fun removeFirst(): Boolean

    fun removeLower(name: String): RemoveLowerResult

    override fun iterator(): Iterator<Vehicle>

    fun sumOfEnginePower(): Float

    fun update(id: Int, vehicle: Vehicle): UpdateResult

    enum class AddIfMinResult(
        val isSuccess: Boolean
    ) {
        EMPTY(true),
        SUCCESS(true),
        LESS_FOUND(false);
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
        val initDate: LocalDateTime
    ) {
        val typeOfCollection = "Средства передвижения"
    }
}

