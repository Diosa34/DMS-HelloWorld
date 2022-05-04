package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection

class RemoveById(
    private val id: Int
): ApplicableToCollection {

    override fun execute(collection: CollectionOfVehicles) {
        if (collection.size == 0) {
            println("Коллекция пуста")
            return@execute
        } else if (collection.any { it.id == id }) {
            collection.removeIf {
                it.id == id
            }
            println("Элемент удалён")
        } else if (collection.none { it.id == id }) {
            println("Элемент с id $id не найден")
        }
    }

    companion object{
        const val title: String = "remove_by_id"
        const val help: String = "удалить элемент из коллекции по его номеру"
    }
}