@file:OptIn(ExperimentalUnsignedTypes::class, ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.*
import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize
import java.io.Serializable

class Add(
    private val vehicle: Vehicle
): ApplicableToCollection, Serializable {
    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        collection.add(vehicle)
        logger.print("Элемент успешно добавлен в коллекцию")
    }

    override fun serialize(): UByteArray{
        var bytes: UByteArray = title.serialize()
        bytes += this.vehicle.serialize()
        return bytes
    }

    companion object Description: AbstractDescription {
        override val title: String = "add"
        override val help: String = "добавить новый элемент в коллекцию"
    }
}