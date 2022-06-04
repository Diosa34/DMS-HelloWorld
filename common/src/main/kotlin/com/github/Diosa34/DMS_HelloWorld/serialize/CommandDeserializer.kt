@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.serialize

import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.commands.*
import com.github.Diosa34.DMS_HelloWorld.exceptions.DeserializeException

object CommandDeserializer {
    fun deserialize(command: UByteArray, logger: Logger): BoundCommand {
        val iterator = command.iterator()
        try {
            val title: String = iterator.deserializeString()
            return when (title) {
                "add" -> Add.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "add_if_min" -> AddIfMin.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "clear" -> Clear.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "count_by_type" -> CountByType.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "exit" -> Exit.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "group_counting_by_type" -> GroupCountingByType.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "help" -> Help.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "info" -> Info.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "remove_by_id" -> RemoveById.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "remove_first" -> RemoveFirst.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "remove_lower" -> RemoveLower.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "show" -> Show.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "sum_of_engine_power" -> SumOfEnginePower.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                "update" -> Update.serializer().deserialize(GeneralServerDecoder(command.toByteArray(), 1, logger, iterator))
                else -> throw DeserializeException("###Соответствующая команда не найдена при десериализации")
            }
        } catch (ex: DeserializeException) {
            throw ex
        } catch (ex: Exception) {
            throw DeserializeException("###Ошибка десериализации", ex)
        }
    }
}