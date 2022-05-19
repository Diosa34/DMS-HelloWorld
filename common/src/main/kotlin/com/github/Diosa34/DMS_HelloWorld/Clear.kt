@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

object Clear : ApplicableToCollection, AbstractDescription {
    override val title: String = "clear"
    override val help: String = "очистить коллекцию"

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        collection.clear()
        logger.print("Коллекция очищена")
    }

    override fun serialize(): UByteArray {
        return title.serialize()
    }
}