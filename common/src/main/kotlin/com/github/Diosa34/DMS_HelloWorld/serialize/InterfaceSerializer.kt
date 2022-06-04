package com.github.Diosa34.DMS_HelloWorld.serialize

import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.commands.*
import com.github.Diosa34.DMS_HelloWorld.exceptions.UnexpectedCommandException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object InterfaceSerializer : KSerializer<BoundCommand> {
    override val descriptor: SerialDescriptor
        get() = TODO("Not yet implemented")

    override fun deserialize(decoder: Decoder): BoundCommand {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: BoundCommand) {
        when (value) {
            is Register -> Register.serializer().serialize(encoder, value)
            is LogIn -> LogIn.serializer().serialize(encoder, value)
            is Add -> Add.serializer().serialize(encoder, value)
            is AddIfMin -> AddIfMin.serializer().serialize(encoder, value)
            is Clear -> Clear.serializer().serialize(encoder, value)
            is CountByType -> CountByType.serializer().serialize(encoder, value)
            is Exit -> Exit.serializer().serialize(encoder, value)
            is GroupCountingByType -> GroupCountingByType.serializer()
                .serialize(encoder, value)
            is Help -> Help.serializer().serialize(encoder, value)
            is Info -> Info.serializer().serialize(encoder, value)
            is RemoveById -> RemoveById.serializer().serialize(encoder, value)
            is RemoveFirst -> RemoveFirst.serializer().serialize(encoder, value)
            is RemoveLower -> RemoveLower.serializer().serialize(encoder, value)
            is Show -> Show.serializer().serialize(encoder, value)
            is SumOfEnginePower -> SumOfEnginePower.serializer().serialize(encoder, value)
            is Update -> Update.serializer().serialize(encoder, value)
            else -> throw UnexpectedCommandException()
        }
    }
}