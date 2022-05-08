package com.github.Diosa34.DMS_HelloWorld

object GroupCountingByType: ApplicableToCollection, AbstractDescription {
    override val title: String = "group_counting_by_type"
    override val help: String = "сгруппировать элементы коллекции по значению типа средства передвижения, вывести" +
            " количество элементов в каждой группе"

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        val countOfCar = collection.count {
            it.type == VehicleType.CAR
        }
        val countOfSubmarine = collection.count {
            it.type == VehicleType.SUBMARINE
        }
        val countOfShip = collection.count {
            it.type == VehicleType.SHIP
        }
        logger.print("$countOfCar - количество машин")
        logger.print("$countOfSubmarine - количество подводных лодок")
        logger.print("$countOfShip - количество кораблей")
    }

    fun serialize(): ByteArray {
        return title.serialize()
    }
}