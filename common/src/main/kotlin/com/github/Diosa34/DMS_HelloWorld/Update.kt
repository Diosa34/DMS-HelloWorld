@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

class Update(
    private val id: Int,
    private val vehicle: Vehicle,
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles){
        if (collection.size > 0) {
            if (collection.none { it.id == this.id }) {
                logger.print("Элементов с соответствующим id не найдено")
                return
            }
            for (elem in collection) {
                if (elem.id == id) {
                    collection[collection.indexOf(elem)] = vehicle
                    logger.print("Элемент успешно обновлён")
                    return
                }
            }
        } else {
            logger.print("Коллекция пуста, элемент НЕ был обновлён")
        }
    }

    override fun serialize(): UByteArray{
        var bytes: UByteArray = title.serialize()
        bytes += this.id.serialize()
        bytes += this.vehicle.serialize()
        return bytes
    }

    companion object Description: AbstractDescription{
        override val title: String = "update"
        override val help: String = "обновить значение элемента коллекции, номер которого равен заданному"
    }
}