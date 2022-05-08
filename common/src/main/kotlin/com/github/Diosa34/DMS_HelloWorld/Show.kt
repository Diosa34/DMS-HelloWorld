package com.github.Diosa34.DMS_HelloWorld

object Show: ApplicableToCollection, AbstractDescription {
     override val title: String = "show"
     override val help: String = "вывести все элементы коллекции"

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        if (collection.size > 0) {
            for (elem in collection) {
                logger.print(elem.toString())
            }
        } else {
            logger.print("Коллекция пуста")
        }
    }

    fun serialize(): ByteArray {
        return title.serialize()
    }
}