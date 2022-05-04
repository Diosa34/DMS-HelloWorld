package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.commands.tryGet
import com.github.Diosa34.DMS_HelloWorld.enums.InstanceCreator
import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractParser
import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractStringReader

class AddIfMin(
    private val attempts: Int,
    private val creator: InstanceCreator,
    private val stringReader: AbstractStringReader
): ApplicableToCollection {
    override fun execute(collection: CollectionOfVehicles) {
        println("Введите марку средства передвижения")
        val name = tryGet(stringReader.getNextLine(), attempts, "Имя не может быть пустой строкой")
        { takeIf { isNotBlank() } } ?: return@execute
        if (collection.size != 0){
            val minElem = collection.sortedWith(compareBy { it.name.length })[0]
            if (minElem > name) {
                collection.add(creator.invoke(stringReader))
            } else {
                println("Найдены элементы с таким же по длине или более коротким названием")
            }
        } else {
            println("Коллекция пуста")
            collection.add(creator.invoke(stringReader))
        }
    }

    companion object{
        const val title: String = "add_if_min"
        const val help: String = "добавить новый элемент в коллекцию, если его значение меньше," +
        " чем у наименьшего элемента этой коллекции (элементы сравниваются по длине марки средства передвижения)"
    }
}