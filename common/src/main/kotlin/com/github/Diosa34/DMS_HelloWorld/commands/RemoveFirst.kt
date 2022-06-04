@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.serialization.Serializable

@Serializable
object RemoveFirst: ApplicableToCollection, AbstractDescription {
    override val title: String = "remove_first"
    override val help: String = "удалить первый элемент из коллекции"

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        if (collection.removeFirst()) {
            logger.print("Первый элемент удалён")
        } else {
            logger.print("Коллекция пуста, нет элементов для удаления")
        }
    }
}