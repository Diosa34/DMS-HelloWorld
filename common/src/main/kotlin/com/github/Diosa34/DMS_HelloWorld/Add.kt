package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.classes.Vehicle
import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection

class Add(
    private val vehicle: Vehicle
): ApplicableToCollection {
    override fun execute(collection: CollectionOfVehicles) {
        CollectionOfVehicles.globalCollection.add(vehicle)
    }

    companion object{
        const val title: String = "show"
        const val help: String = "вывести все элементы коллекции"
    }
}