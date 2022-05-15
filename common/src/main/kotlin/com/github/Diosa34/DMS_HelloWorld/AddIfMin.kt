@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

class AddIfMin(
    private val name: String,
    private val vehicle: Vehicle
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        if (CollectionOfVehicles.globalCollection.size != 0){
            val minElem = CollectionOfVehicles.globalCollection.sortedWith(compareBy { it.name.length })[0]
            if (minElem.compareTo(name) > 0) {
                CollectionOfVehicles.globalCollection.add(vehicle)
                logger.print("Элемент успешно добавлен в коллекцию")
            } else {
                logger.print("Найдены элементы с таким же по длине или более коротким названием, новый элемент НЕ был добавлен")
            }
        } else {
            CollectionOfVehicles.globalCollection.add(vehicle)
            logger.print("Коллекция была пуста, элемент успешно добавлен")
        }
    }

    override fun serialize(): UByteArray{
        var bytes: UByteArray = title.serialize()
        bytes += this.name.serialize()
        bytes += this.vehicle.serialize()
        return bytes
    }

    companion object Description: AbstractDescription {
        override val title: String = "add_if_min"
        override val help: String = "добавить новый элемент в коллекцию, если его значение меньше," +
        " чем у наименьшего элемента этой коллекции (элементы сравниваются по длине марки средства передвижения)"
    }
}