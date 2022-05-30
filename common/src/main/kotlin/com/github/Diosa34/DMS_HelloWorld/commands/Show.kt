@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize

object Show: ApplicableToCollection, AbstractDescription {
     override val title: String = "show"
     override val help: String = "вывести все элементы коллекции"

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        if (collection.iterator().hasNext()) {
            for (i in collection) {
                logger.print(i.toString())
            }
        } else {
            logger.print("Коллекция пуста")
        }
        collection.print()
    }

    override fun serialize(): UByteArray {
        return title.serialize()
    }
}