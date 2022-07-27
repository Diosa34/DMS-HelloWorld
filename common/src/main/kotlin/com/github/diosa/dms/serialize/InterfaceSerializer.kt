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
                "Registry" -> SignUp.serializer().deserialize(decoder)
                "Log in" -> LogIn.serializer().deserialize(decoder)
                "Add" -> Add.serializer().deserialize(decoder)
                "Add if min" -> AddIfMin.serializer().deserialize(decoder)
                "Clear" -> Clear.serializer().deserialize(decoder)
                "Count by type" -> CountByType.serializer().deserialize(decoder)
                "Exit" -> Exit.serializer().deserialize(decoder)
                "Group counting by type" -> GroupCountingByType.serializer().deserialize(decoder)
                "Help" -> Help.serializer().deserialize(decoder)
                "Info" -> Info.serializer().deserialize(decoder)
                "Remove by id" -> RemoveById.serializer().deserialize(decoder)
                "Remove first" -> RemoveFirst.serializer().deserialize(decoder)
                "Remove lower" -> RemoveLower.serializer().deserialize(decoder)
                "Show" -> Show.serializer().deserialize(decoder)
                "Sum of engine power" -> SumOfEnginePower.serializer().deserialize(decoder)
                "Update" -> Update.serializer().deserialize(decoder)
                "getCollection" -> GetCollection.serializer().deserialize(decoder)
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
            is GetCollection -> GetCollection.serializer().encodeWithSerialName(encoder, value)
            else -> throw UnexpectedCommandException()
        }
    }
}