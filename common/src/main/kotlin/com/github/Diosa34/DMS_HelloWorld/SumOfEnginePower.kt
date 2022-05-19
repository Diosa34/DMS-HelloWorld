@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

object SumOfEnginePower: ApplicableToCollection, AbstractDescription {
    override val title: String = "sum_of_engine_power"
    override val help: String = "вывести сумму значений мощностей двигателей всех элементов"

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        logger.print("Суммарная мощность двигателей: ${collection.sumOfEnginePower()}")
    }

    override fun serialize(): UByteArray {
        return title.serialize()
    }
}