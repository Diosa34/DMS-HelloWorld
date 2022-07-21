package com.github.diosa.dms.serialize

import com.github.diosa.dms.absctactions.BoundCommand
import com.github.diosa.dms.commands.*
import com.github.diosa.dms.exceptions.DeserializeException
import com.github.diosa.dms.exceptions.UnexpectedCommandException
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object InterfaceSerializer : KSerializer<BoundCommand> {
    override val descriptor: SerialDescriptor
        get() = TODO()

    override fun deserialize(decoder: Decoder): BoundCommand {
        try {
            return when (decoder.decodeString()) {
                "registry" -> SignUp.serializer().deserialize(decoder)
                "log_in" -> LogIn.serializer().deserialize(decoder)
                "add" -> Add.serializer().deserialize(decoder)
                "add_if_min" -> AddIfMin.serializer().deserialize(decoder)
                "clear" -> Clear.serializer().deserialize(decoder)
                "count_by_type" -> CountByType.serializer().deserialize(decoder)
                "exit" -> Exit.serializer().deserialize(decoder)
                "group_counting_by_type" -> GroupCountingByType.serializer().deserialize(decoder)
                "help" -> Help.serializer().deserialize(decoder)
                "info" -> Info.serializer().deserialize(decoder)
                "remove_by_id" -> RemoveById.serializer().deserialize(decoder)
                "remove_first" -> RemoveFirst.serializer().deserialize(decoder)
                "remove_lower" -> RemoveLower.serializer().deserialize(decoder)
                "show" -> Show.serializer().deserialize(decoder)
                "sum_of_engine_power" -> SumOfEnginePower.serializer().deserialize(decoder)
                "update" -> Update.serializer().deserialize(decoder)
                else -> throw DeserializeException("###Соответствующая команда не найдена при десериализации")
            }
        } catch (ex: DeserializeException) {
            throw ex
        } catch (ex: Exception) {
            throw DeserializeException("###Ошибка десериализации", ex)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Suppress("NOTHING_TO_INLINE")
    private inline fun <T>KSerializer<T>.encodeWithSerialName(encoder: Encoder, value: T) {
        encoder.encodeString(this.descriptor.serialName)
        this.serialize(encoder, value)
    }

    override fun serialize(encoder: Encoder, value: BoundCommand) {
        when (value) {
            is SignUp -> SignUp.serializer().encodeWithSerialName(encoder, value)
            is LogIn -> LogIn.serializer().encodeWithSerialName(encoder, value)
            is Add -> Add.serializer().encodeWithSerialName(encoder, value)
            is AddIfMin -> AddIfMin.serializer().encodeWithSerialName(encoder, value)
            is Clear -> Clear.serializer().encodeWithSerialName(encoder, value)
            is CountByType -> CountByType.serializer().encodeWithSerialName(encoder, value)
            is Exit -> Exit.serializer().encodeWithSerialName(encoder, value)
            is GroupCountingByType -> GroupCountingByType.serializer()
                .encodeWithSerialName(encoder, value)
            is Help -> Help.serializer().encodeWithSerialName(encoder, value)
            is Info -> Info.serializer().encodeWithSerialName(encoder, value)
            is RemoveById -> RemoveById.serializer().encodeWithSerialName(encoder, value)
            is RemoveFirst -> RemoveFirst.serializer().encodeWithSerialName(encoder, value)
            is RemoveLower -> RemoveLower.serializer().encodeWithSerialName(encoder, value)
            is Show -> Show.serializer().encodeWithSerialName(encoder, value)
            is SumOfEnginePower -> SumOfEnginePower.serializer().encodeWithSerialName(encoder, value)
            is Update -> Update.serializer().encodeWithSerialName(encoder, value)
            else -> throw UnexpectedCommandException()
        }
    }
}