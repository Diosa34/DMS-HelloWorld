package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.enums.VehicleType

object GroupCountingByType: ApplicableToCollection {
    const val title: String = "add_if_min"
    const val help: String = "сгруппировать элементы коллекции по значению типа средства передвижения, вывести" +
            " количество элементов в каждой группе"

    override fun execute(collection: CollectionOfVehicles) {
        val countOfCar = collection.count {
            it.type == VehicleType.CAR
        }
        val countOfSubmarine = collection.count {
            it.type == VehicleType.SUBMARINE
        }
        val countOfShip = collection.count {
            it.type == VehicleType.SHIP
        }
        println("$countOfCar - количество машин")
        println("$countOfSubmarine - количество подводных лодок")
        println("$countOfShip - количество кораблей")
    }
}