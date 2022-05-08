package com.github.Diosa34.DMS_HelloWorld

class RemoveLower(
    private val name: String
): ApplicableToCollection {

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        if (CollectionOfVehicles.globalCollection!!.size != 0) {
            if (collection.any { it < name }) {
                CollectionOfVehicles.globalCollection.removeIf { elem ->
                    elem < name
                }
                logger.print("Элементы удалены")
            } else {
                logger.print("Элементов с более короткой маркой не найдено")
            }
        } else {
            logger.print("Коллекция пуста")
        }
    }

    fun serialize(): ByteArray{
        var bytes: ByteArray = title.serialize()
        bytes += this.name.serialize()
        return bytes
    }

    companion object Description: AbstractDescription{
        override val title: String = "remove_lower"
        override val help: String = "удалить из коллекции все элементы, меньшие, чем" +
                " заданный (элементы сравниваются по длине марки средства передвижения)"
    }
}