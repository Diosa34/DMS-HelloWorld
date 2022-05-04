package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection

object Show: ApplicableToCollection {
    const val title: String = "show"
    const val help: String = "вывести все элементы коллекции"

    override fun execute(collection: CollectionOfVehicles) {
        if (collection.size > 0) {
            for (elem in collection) {
                println(elem.toString())
            }
        } else {
            println("Коллекция пуста")
        }
    }
}