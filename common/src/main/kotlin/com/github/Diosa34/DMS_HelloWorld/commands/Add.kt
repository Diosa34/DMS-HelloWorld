package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.collection.Vehicle
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.serialization.Serializable

@Serializable
class Add(
    private val vehicle: Vehicle
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        collection.add(vehicle, user)
        logger.print("Элемент успешно добавлен в коллекцию")
    }

    companion object Description: AbstractDescription {
        override val title: String = "add"
        override val help: String = "добавить новый элемент в коллекцию"
    }
}