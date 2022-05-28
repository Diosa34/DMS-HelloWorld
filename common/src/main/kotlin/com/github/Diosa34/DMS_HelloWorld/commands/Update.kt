@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.*
import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize

class Update(
    private val id: Int,
    private val vehicle: Vehicle,
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles){
        when (collection.update(id, vehicle)) {
            CollectionOfVehicles.UpdateResult.EMPTY -> logger.print("Коллекция пуста, элемент НЕ был обновлён")
            CollectionOfVehicles.UpdateResult.UPDATED -> logger.print("Элемент успешно обновлён")
            CollectionOfVehicles.UpdateResult.NOT_FOUND -> logger.print("Элементов с соответствующим id не найдено")
        }
    }

    override fun serialize(): UByteArray{
        var bytes: UByteArray = title.serialize()
        bytes += this.id.serialize()
        bytes += this.vehicle.serialize()
        return bytes
    }

    companion object Description: AbstractDescription {
        override val title: String = "update"
        override val help: String = "обновить значение элемента коллекции, номер которого равен заданному"
    }
}