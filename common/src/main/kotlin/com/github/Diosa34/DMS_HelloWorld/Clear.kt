package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection

object Clear: ApplicableToCollection {
    const val title: String = "clear"
    const val help: String = "очистить коллекцию"

    override fun execute(collection: CollectionOfVehicles){
        if (collection.size > 0) {
            collection.clear()
            println("Коллекция очищена")
        } else {
            println("Коллекция пуста")
        }
    }
}