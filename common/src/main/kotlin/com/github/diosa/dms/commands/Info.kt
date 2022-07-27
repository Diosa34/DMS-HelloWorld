package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.ApplicableToCollection
import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.users.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Info")
object Info: ApplicableToCollection, AbstractDescription {
    override val title: String = "Info"
    override val help: String = "вывести информацию о коллекции"

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User){
        logger.print(collection.info().typeOfCollection + ";")
        logger.print(collection.info().initDate.toString() + ";")
        logger.print(collection.info().elemCount.toString())
    }
}