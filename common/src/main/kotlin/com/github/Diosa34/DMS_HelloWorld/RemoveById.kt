package com.github.Diosa34.DMS_HelloWorld

class RemoveById(
    private val id: Int
): ApplicableToCollection {

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        if (collection.size == 0) {
            logger.print("Коллекция пуста")
            return@execute
        } else if (collection.any { it.id == id }) {
            collection.removeIf {
                it.id == id
            }
            logger.print("Элемент удалён")
        } else if (collection.none { it.id == id }) {
            logger.print("Элемент с id $id не найден")
        }
    }

    fun serialize(): ByteArray{
        var bytes: ByteArray = title.serialize()
        bytes += this.id.serialize()
        return bytes
    }

    companion object Description: AbstractDescription{
        override val title: String = "remove_by_id"
        override val help: String = "удалить элемент из коллекции по его номеру"
    }
}