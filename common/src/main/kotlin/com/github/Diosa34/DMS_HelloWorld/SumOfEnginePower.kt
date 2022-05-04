package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection

object SumOfEnginePower: ApplicableToCollection {
    const val title: String = "add_if_min"
    const val help: String = "вывести сумму значений мощностей двигателей всех элементов"

    override fun execute(collection: CollectionOfVehicles) {
        if (collection.size == 0) {
            println("Коллекция пуста")
        } else {
            var summa = 0.0
            for (elem in collection) {
                summa += elem.enginePower
            }
            println(summa)
        }
    }
}