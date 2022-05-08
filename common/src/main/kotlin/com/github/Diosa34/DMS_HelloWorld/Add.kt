package com.github.Diosa34.DMS_HelloWorld

import java.io.Serializable

class Add(
    private val vehicle: Vehicle
): ApplicableToCollection, Serializable {
    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        collection.add(vehicle)
    }

    fun serialize(): ByteArray{
        var bytes: ByteArray = title.serialize()
        bytes += this.vehicle.serialize()
        return bytes
    }

    companion object Description: AbstractDescription{
        override val title: String = "add"
        override val help: String = "добавить новый элемент в коллекцию"
    }
}