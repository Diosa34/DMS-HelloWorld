package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection

class RemoveLower(
    private val name: String
): ApplicableToCollection {

    override fun execute(collection: CollectionOfVehicles) {
        if (CollectionOfVehicles.globalCollection!!.size != 0) {
            if (collection.any { it < name }) {
                CollectionOfVehicles.globalCollection.removeIf { elem ->
                    elem < name
                }
                println("Элементы удалены")
            } else {
                println("Элементов с более короткой маркой не найдено")
            }
        } else {
            println("Коллекция пуста")
        }
    }

    companion object{
        const val title: String = "remove_lower"
        const val help: String = "удалить из коллекции все элементы, меньшие, чем" +
                " заданный (элементы сравниваются по длине марки средства передвижения)"
    }
}