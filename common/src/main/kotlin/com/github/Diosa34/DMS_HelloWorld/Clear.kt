@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

object Clear : ApplicableToCollection, AbstractDescription {
    override val title: String = "clear"
    override val help: String = "очистить коллекцию"

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        if (collection.size > 0) {
            collection.clear()
            logger.print("Коллекция очищена")
        } else {
            logger.print("Коллекция была пуста")
        }
    }

    override fun serialize(): UByteArray {
        return title.serialize()
    }
}