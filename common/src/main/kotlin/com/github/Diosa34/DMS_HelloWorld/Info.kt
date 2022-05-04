package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection

object Info: ApplicableToCollection {
    const val title: String = "info"
    const val help: String = "вывести информацию о коллекции"

    override fun execute(collection: CollectionOfVehicles){
        println("Тип: средства передвижения")
        println("Количество элементов: ${collection.size}")
        if (collection.size > 0) {
            val minDate = collection.minOf { elem -> elem.creationDate }
            println("Дата инициализации: $minDate")
        } else {
            println("Коллекция не проинициализирована")
        }
    }

//    fun <T, A, R> execute(arg: T, f: (T, A) -> R) : (A) -> R = {a ->
//        f(arg, a)
//    }
}