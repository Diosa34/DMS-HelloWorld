package com.github.Diosa34.DMS_HelloWorld

object SumOfEnginePower: ApplicableToCollection, AbstractDescription {
    override val title: String = "add_if_min"
    override val help: String = "вывести сумму значений мощностей двигателей всех элементов"

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        if (collection.size == 0) {
            logger.print("Коллекция пуста")
        } else {
            var summa = 0.0
            for (elem in collection) {
                summa += elem.enginePower
            }
            logger.print("Суммарная мощность двигателей: $summa")
        }
    }

    override fun serialize(): ByteArray {
        return title.serialize()
    }
}