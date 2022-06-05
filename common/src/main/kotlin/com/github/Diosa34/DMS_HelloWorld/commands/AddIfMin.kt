@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.*
import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.collection.Vehicle
import com.github.Diosa34.DMS_HelloWorld.users.User
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("add_if_min")
class AddIfMin(
    @DisplayName("Марка средства передвижения")
    private val name: String,
    @DisplayName("Средство передвижения")
    private val vehicle: Vehicle
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        when (collection.addIfMin(name, vehicle, user).first) {
            CollectionOfVehicles.AddIfMinResult.EMPTY -> logger.print("Коллекция была пуста, элемент успешно добавлен")
            CollectionOfVehicles.AddIfMinResult.SUCCESS -> logger.print("Элемент успешно добавлен в коллекцию")
            CollectionOfVehicles.AddIfMinResult.LESS_FOUND -> logger.print("Найдены элементы с таким же по длине или более коротким названием, новый элемент НЕ был добавлен")
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "add_if_min"
        override val help: String = "добавить новый элемент в коллекцию, если его значение меньше," +
        " чем у наименьшего элемента этой коллекции (элементы сравниваются по длине марки средства передвижения)"
    }
}