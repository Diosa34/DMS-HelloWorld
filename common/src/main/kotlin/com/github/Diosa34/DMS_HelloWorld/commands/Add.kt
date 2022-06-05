package com.github.Diosa34.DMS_HelloWorld.commands

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
@SerialName("add")
class Add(
    @DisplayName("Средство передвижения")
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