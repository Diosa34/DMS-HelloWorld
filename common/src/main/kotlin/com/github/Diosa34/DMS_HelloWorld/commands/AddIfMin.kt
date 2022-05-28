@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.*
import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize

class AddIfMin(
    private val name: String,
    private val vehicle: Vehicle
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        when (collection.addIfMin(name, vehicle)) {
            CollectionOfVehicles.AddIfMinResult.EMPTY -> logger.print("Коллекция была пуста, элемент успешно добавлен")
            CollectionOfVehicles.AddIfMinResult.SUCCESS -> logger.print("Элемент успешно добавлен в коллекцию")
            CollectionOfVehicles.AddIfMinResult.LESS_FOUND -> logger.print("Найдены элементы с таким же по длине или более коротким названием, новый элемент НЕ был добавлен")
        }
    }

    override fun serialize(): UByteArray{
        var bytes: UByteArray = title.serialize()
        bytes += this.name.serialize()
        bytes += this.vehicle.serialize()
        return bytes
    }

    companion object Description: AbstractDescription {
        override val title: String = "add_if_min"
        override val help: String = "добавить новый элемент в коллекцию, если его значение меньше," +
        " чем у наименьшего элемента этой коллекции (элементы сравниваются по длине марки средства передвижения)"
    }
}