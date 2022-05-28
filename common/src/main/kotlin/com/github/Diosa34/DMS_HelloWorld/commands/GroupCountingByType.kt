@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize

object GroupCountingByType: ApplicableToCollection, AbstractDescription {
    override val title: String = "group_counting_by_type"
    override val help: String = "сгруппировать элементы коллекции по значению типа средства передвижения, вывести" +
            " количество элементов в каждой группе"

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        logger.print("${collection.groupCountingByType().countOfCar} - количество машин")
        logger.print("${collection.groupCountingByType().countOfSubmarine} - количество подводных лодок")
        logger.print("${collection.groupCountingByType().countOfShip} - количество кораблей")
    }

    override fun serialize(): UByteArray {
        return title.serialize()
    }
}