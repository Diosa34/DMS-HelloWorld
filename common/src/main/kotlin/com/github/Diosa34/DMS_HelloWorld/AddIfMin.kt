package com.github.Diosa34.DMS_HelloWorld

class AddIfMin(
    private val name: String,
    private val vehicle: Vehicle
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        if (CollectionOfVehicles.globalCollection.size != 0){
            val minElem = CollectionOfVehicles.globalCollection.sortedWith(compareBy { it.name.length })[0]
            if (minElem > name) {
                CollectionOfVehicles.globalCollection.add(vehicle)
            } else {
                logger.print("Найдены элементы с таким же по длине или более коротким названием")
            }
        } else {
            logger.print("Коллекция пуста")
            CollectionOfVehicles.globalCollection.add(vehicle)
        }
    }

    fun serialize(): ByteArray{
        var bytes: ByteArray = title.serialize()
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