package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.ApplicableToCollection
import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.users.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("group_counting_by_type")
object GroupCountingByType: ApplicableToCollection, AbstractDescription {
    override val title: String = "group_counting_by_type"
    override val help: String = "сгруппировать элементы коллекции по значению типа средства передвижения, вывести" +
            " количество элементов в каждой группе"

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        logger.print("${collection.groupCountingByType().countOfCar} - количество машин")
        logger.print("${collection.groupCountingByType().countOfSubmarine} - количество подводных лодок")
        logger.print("${collection.groupCountingByType().countOfShip} - количество кораблей")
    }
}