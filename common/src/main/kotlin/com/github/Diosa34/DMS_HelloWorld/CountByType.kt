package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.enums.VehicleType

class CountByType(
    private val type: VehicleType
): ApplicableToCollection {
    override fun execute(collection: CollectionOfVehicles) {
        val count = collection.count {
            it.type == type
        }
        println(count)
    }

    companion object{
        const val title: String = "count_by_type"
        const val help: String = "вывести количество элементов, значение типа которых равно заданному"
    }
}