package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("sum_of_engine_power")
object SumOfEnginePower: ApplicableToCollection, AbstractDescription {
    override val title: String = "sum_of_engine_power"
    override val help: String = "вывести сумму значений мощностей двигателей всех элементов"

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        logger.print("Суммарная мощность двигателей: ${collection.sumOfEnginePower()}")
    }
}