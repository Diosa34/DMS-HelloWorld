package com.github.Diosa34.DMS_HelloWorld

class CountByType(
    private val type: VehicleType
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        val count = collection.count {
            it.type == type
        }
        logger.print(count.toString())
    }

    fun serialize(): ByteArray{
        var bytes: ByteArray = title.serialize()
        bytes += this.type.serialize()
        return bytes
    }

    companion object Description: AbstractDescription{
        override val title: String = "count_by_type"
        override val help: String = "вывести количество элементов, значение типа которых равно заданному"
    }
}