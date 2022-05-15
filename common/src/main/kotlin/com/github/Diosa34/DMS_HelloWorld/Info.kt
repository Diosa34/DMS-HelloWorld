@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

object Info: ApplicableToCollection, AbstractDescription {
    override val title: String = "info"
    override val help: String = "вывести информацию о коллекции"

    override fun execute(logger: Logger, collection: CollectionOfVehicles){
        logger.print("Тип: средства передвижения")
        logger.print("Количество элементов: ${collection.size}")
        if (collection.size > 0) {
            val minDate = collection.minOf { elem -> elem.creationDate }
            logger.print("Дата инициализации: $minDate")
        } else {
            logger.print("Коллекция не проинициализирована")
        }
    }

    override fun serialize(): UByteArray {
        return title.serialize()
    }

//    fun <T, A, R> execute(arg: T, f: (T, A) -> R) : (A) -> R = {a ->
//        f(arg, a)
//    }
}