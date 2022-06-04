@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.serialization.Serializable

@Serializable
class RemoveLower(
    private val name: String
): ApplicableToCollection {

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        when (collection.removeLower(name)) {
            CollectionOfVehicles.RemoveLowerResult.EMPTY -> logger.print("Коллекция пуста, нет элементов для удаления")
            CollectionOfVehicles.RemoveLowerResult.DELETED -> logger.print("Элементы удалены")
            CollectionOfVehicles.RemoveLowerResult.LESS_NOT_FOUND -> logger.print("Элементов с более короткой маркой не найдено")
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "remove_lower"
        override val help: String = "удалить из коллекции все элементы, меньшие, чем" +
                " заданный (элементы сравниваются по длине марки средства передвижения)"
    }
}