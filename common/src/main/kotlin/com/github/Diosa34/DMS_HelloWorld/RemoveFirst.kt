package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection

object RemoveFirst: ApplicableToCollection {
    const val title: String = "remove_first"
    const val help: String = "удалить первый элемент из коллекции"

    override fun execute(collection: CollectionOfVehicles) {
        if (collection.size > 0) {
            collection.removeFirst()
            println("Первый элемент удалён")
        } else {
            println("Коллекция пуста")
        }
    }
}