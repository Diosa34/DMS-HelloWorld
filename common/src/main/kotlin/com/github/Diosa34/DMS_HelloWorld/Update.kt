package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.enums.InstanceCreator
import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractParser
import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractStringReader

class Update(
    private val id: Int,
    private val creator: InstanceCreator,
    private val stringReader: AbstractStringReader
): ApplicableToCollection {
    override fun execute(collection: CollectionOfVehicles){
        if (collection.size > 0) {
            for (elem in collection) {
                if (elem.id == id) {
                    collection[collection.indexOf(elem)] =
                        creator.invoke(stringReader)
                }
            }
        } else {
            println("Коллекция пуста")
        }
    }

    companion object{
        const val title: String = "update"
        const val help: String = "обновить значение элемента коллекции, номер которого равен заданному"
    }
}