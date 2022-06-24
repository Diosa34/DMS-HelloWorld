package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.ApplicableToCollection
import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.users.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("clear")
object Clear : ApplicableToCollection, AbstractDescription {
    override val title: String = "clear"
    override val help: String = "очистить коллекцию"

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        collection.clear(user)
        logger.print("Удалены все элементы принадлежащие вам")
    }
}