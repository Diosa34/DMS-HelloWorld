package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.ApplicableToCollection
import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.users.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Group counting by type")
object GroupCountingByType: ApplicableToCollection, AbstractDescription {
    override val title: String = "Group counting by type"
    override val help: String = "сгруппировать элементы коллекции по значению типа средства передвижения, вывести" +
            " количество элементов в каждой группе"

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        logger.print("${collection.groupCountingByType().countOfCar};")
        logger.print("${collection.groupCountingByType().countOfSubmarine};")
        logger.print("${collection.groupCountingByType().countOfShip}")
    }
}