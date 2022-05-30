@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize

class RemoveById(
    private val id: Int
): ApplicableToCollection {

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        when (collection.removeById(id)) {
            CollectionOfVehicles.RemoveByIdResult.EMPTY -> logger.print("Коллекция пуста, элемент с id $id не удалён")
            CollectionOfVehicles.RemoveByIdResult.DELETED -> logger.print("Элемент c id $id успешно удалён")
            CollectionOfVehicles.RemoveByIdResult.NOT_FOUND -> logger.print("Элемент с id $id не найден")
        }
    }

    override fun serialize(): UByteArray{
        var bytes: UByteArray = title.serialize()
        bytes += this.id.serialize()
        return bytes
    }

    companion object Description: AbstractDescription {
        override val title: String = "remove_by_id"
        override val help: String = "удалить элемент из коллекции по его номеру"
    }
}