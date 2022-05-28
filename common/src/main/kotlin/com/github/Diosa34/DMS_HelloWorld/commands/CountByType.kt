@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.collection.VehicleType
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize

class CountByType(
    private val type: VehicleType
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        logger.print("Количество средств передвижения типа ${this.type}:" +
                " ${collection.countByType(type)}")
    }

    override fun serialize(): UByteArray {
        var bytes: UByteArray = title.serialize()
        bytes += this.type.serialize()
        return bytes
    }

    companion object Description: AbstractDescription {
        override val title: String = "count_by_type"
        override val help: String = "вывести количество элементов, значение типа которых равно заданному"
    }
}