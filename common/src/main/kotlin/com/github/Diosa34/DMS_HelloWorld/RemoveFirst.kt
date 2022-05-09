package com.github.Diosa34.DMS_HelloWorld

object RemoveFirst: ApplicableToCollection, AbstractDescription {
    override val title: String = "remove_first"
    override val help: String = "удалить первый элемент из коллекции"

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        if (collection.size > 0) {
            collection.removeFirst()
            logger.print("Первый элемент удалён")
        } else {
            logger.print("Коллекция пуста, нет элементов для удаления")
        }
    }

    override fun serialize(): ByteArray {
        return title.serialize()
    }
}