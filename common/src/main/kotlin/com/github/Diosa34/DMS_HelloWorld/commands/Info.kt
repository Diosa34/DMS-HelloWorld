@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize

object Info: ApplicableToCollection, AbstractDescription {
    override val title: String = "info"
    override val help: String = "вывести информацию о коллекции"

    override fun execute(logger: Logger, collection: CollectionOfVehicles){
        logger.print(collection.info().typeOfCollection)
        logger.print(collection.info().initDate.toString())
        logger.print(collection.info().elemCount.toString())
    }

    override fun serialize(): UByteArray {
        return title.serialize()
    }
}