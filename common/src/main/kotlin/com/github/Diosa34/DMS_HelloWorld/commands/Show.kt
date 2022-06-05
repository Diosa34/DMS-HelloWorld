@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("show")
object Show: ApplicableToCollection, AbstractDescription {
     override val title: String = "show"
     override val help: String = "вывести все элементы коллекции"

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        if (collection.iterator().hasNext()) {
            for (i in collection) {
                logger.print(i.toString())
            }
        } else {
            logger.print("Коллекция пуста")
        }
        collection.print()
    }
}