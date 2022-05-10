package com.github.Diosa34.DMS_HelloWorld

object CommandDeserializer {
    fun deserialize(command: ByteArray): BoundCommand{
        val iterator = command.iterator()
        try {
            val title: String = iterator.deserializeString()
            return when (title) {
                "add" -> Add(iterator.deserializeVehicle())
                "add_if_min" -> AddIfMin(iterator.deserializeString(), iterator.deserializeVehicle())
                "clear" -> Clear
                "count_by_type" -> CountByType(iterator.deserializeVehicleType())
                "exit" -> Exit
                "group_counting_by_type" -> GroupCountingByType
                "help" -> Help
                "info" -> Info
                "remove_by_id" -> RemoveById(iterator.deserializeInt())
                "remove_first" -> RemoveFirst
                "remove_lower" -> RemoveLower(iterator.deserializeString())
                "show" -> Show
                "sum_of_engine_power" -> SumOfEnginePower
                "update" -> Update(iterator.deserializeInt(), iterator.deserializeVehicle())
                else -> throw DeserializeException("###Соответствующая команда не найдена при десериализации")
            }
        } catch (ex: DeserializeException) {
            throw ex
        } catch (ex: Exception) {
            throw DeserializeException("###Ошибка десериализации", ex)
        }
    }
}