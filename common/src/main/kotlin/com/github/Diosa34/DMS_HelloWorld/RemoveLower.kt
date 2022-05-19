@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

class RemoveLower(
    private val name: String
): ApplicableToCollection {

    override fun execute(logger: Logger, collection: CollectionOfVehicles) {
        when (collection.removeLower(name)) {
            CollectionOfVehicles.RemoveLowerResult.EMPTY -> logger.print("Коллекция пуста, нет элементов для удаления")
            CollectionOfVehicles.RemoveLowerResult.DELETED -> logger.print("Элементы удалены")
            CollectionOfVehicles.RemoveLowerResult.LESS_NOT_FOUND -> logger.print("Элементов с более короткой маркой не найдено")
        }
    }

    override fun serialize(): UByteArray{
        var bytes: UByteArray = title.serialize()
        bytes += this.name.serialize()
        return bytes
    }

    companion object Description: AbstractDescription{
        override val title: String = "remove_lower"
        override val help: String = "удалить из коллекции все элементы, меньшие, чем" +
                " заданный (элементы сравниваются по длине марки средства передвижения)"
    }
}