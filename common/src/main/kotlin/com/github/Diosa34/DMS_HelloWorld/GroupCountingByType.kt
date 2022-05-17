package com.github.Diosa34.DMS_HelloWorld

object GroupCountingByType: ApplicableToCollection, AbstractDescription {
    override val title: String = "group_counting_by_type"
    override val help: String = "сгруппировать элементы коллекции по значению типа средства передвижения, вывести" +
            " количество элементов в каждой группе"

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        logger.print("${collection.groupCountingByType().countOfCar} - количество машин")
        logger.print("${collection.groupCountingByType().countOfSubmarine} - количество подводных лодок")
        logger.print("${collection.groupCountingByType().countOfShip} - количество кораблей")
    }

    override fun serialize(): ByteArray {
        return title.serialize()
    }
}