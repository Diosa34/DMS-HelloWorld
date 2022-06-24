package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.ApplicableToCollection
import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.users.User
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
    }
}