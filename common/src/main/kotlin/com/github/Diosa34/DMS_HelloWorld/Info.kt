package com.github.Diosa34.DMS_HelloWorld

object Info: ApplicableToCollection, AbstractDescription {
    override val title: String = "info"
    override val help: String = "вывести информацию о коллекции"

    override fun execute(logger: Logger, collection: CollectionOfVehicles){
        logger.print(collection.info().typeOfCollection)
        logger.print(collection.info().initDate.toString())
        logger.print(collection.info().elemCount.toString())
    }

    override fun serialize(): ByteArray {
        return title.serialize()
    }
}