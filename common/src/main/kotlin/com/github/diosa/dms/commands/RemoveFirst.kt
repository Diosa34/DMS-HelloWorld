package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.ApplicableToCollection
import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.users.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Remove first")
object RemoveFirst: ApplicableToCollection, AbstractDescription {
    override val title: String = "Remove first"
    override val help: String = "удалить первый элемент из коллекции"

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        if (collection.removeFirst(user)) {
            logger.print("Первый элемент коллекции удалён")
        } else {
            logger.print("Коллекция пуста или первый элемент из неё принадлежит другому пользователю")
        }
    }
}