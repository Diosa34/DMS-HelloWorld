@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize

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