package com.github.Diosa34.DMS_HelloWorld.serialize

import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.commands.*
import com.github.Diosa34.DMS_HelloWorld.exceptions.UnexpectedCommandException
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.datetime.Instant
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

class GeneralEncoder @OptIn(ExperimentalUnsignedTypes::class) constructor(
    private var arr: UByteArray,
): Encoder {
    @OptIn(ExperimentalSerializationApi::class)
    override val serializersModule: SerializersModule
        get() = EmptySerializersModule

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        return CompositeEncoderDelegate(this.arr)
    }

    override fun encodeBoolean(value: Boolean) {}

    override fun encodeByte(value: Byte) {
    }

    override fun encodeChar(value: Char) {
    }

    override fun encodeDouble(value: Double) {
    }

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
        this.arr += index.serial()
    }

    override fun encodeFloat(value: Float) {
        this.arr += value.toRawBits().serial()
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @ExperimentalSerializationApi
    override fun encodeInline(inlineDescriptor: SerialDescriptor): Encoder {
        return GeneralEncoder(UByteArray(0))
    }

    override fun encodeInt(value: Int) {
        arr += numberSerialise(value.toUInt().toULong(), 4)
    }

    override fun encodeLong(value: Long) {
        arr += numberSerialise(value.toULong(), 8)
    }

    @ExperimentalSerializationApi
    override fun encodeNull() {
        TODO()
    }

    override fun encodeShort(value: Short) {
    }

    override fun encodeString(value: String) {
        val str: UByteArray = value.toByteArray(Charsets.UTF_8).toUByteArray()
        this.arr += str.size.serial() + str
    }
}

class CompositeEncoderDelegate(
    var arr: UByteArray
): CompositeEncoder{
    override val serializersModule: SerializersModule
        get() = EmptySerializersModule

    override fun encodeBooleanElement(descriptor: SerialDescriptor, index: Int, value: Boolean) {
    }

    override fun encodeByteElement(descriptor: SerialDescriptor, index: Int, value: Byte) {
    }

    override fun encodeCharElement(descriptor: SerialDescriptor, index: Int, value: Char) {
    }

    override fun encodeDoubleElement(descriptor: SerialDescriptor, index: Int, value: Double) {
    }

    override fun encodeFloatElement(descriptor: SerialDescriptor, index: Int, value: Float) {
        this.arr += value.toRawBits().serial()
    }

    @ExperimentalSerializationApi
    override fun encodeInlineElement(descriptor: SerialDescriptor, index: Int): Encoder {
        return GeneralEncoder(UByteArray(0))
    }

    override fun encodeIntElement(descriptor: SerialDescriptor, index: Int, value: Int) {
        arr += numberSerialise(value.toUInt().toULong(), 4)
    }

    override fun encodeLongElement(descriptor: SerialDescriptor, index: Int, value: Long) {
        arr += numberSerialise(value.toULong(), 8)
    }

    @ExperimentalSerializationApi
    override fun <T : Any> encodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        serializer: SerializationStrategy<T>,
        value: T?
    ) {
        val a = value ?: throw NullPointerException()
        serializer.serialize(GeneralEncoder(this.arr), a)
    }

    override fun <T> encodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        serializer: SerializationStrategy<T>,
        value: T
    ) {
        serializer.serialize(GeneralEncoder(this.arr), value)
    }

    override fun encodeShortElement(descriptor: SerialDescriptor, index: Int, value: Short) {
    }

    override fun encodeStringElement(descriptor: SerialDescriptor, index: Int, value: String) {
        val str: UByteArray = value.toByteArray(Charsets.UTF_8).toUByteArray()
        this.arr += str.size.serial() + str
    }

    override fun endStructure(descriptor: SerialDescriptor) {
    }
}

@Suppress("NOTHING_TO_INLINE")
private inline fun numberSerialise(number: ULong, l: Int): UByteArray {
    @Suppress("NAME_SHADOWING")
    var number = number
    val bytes = mutableListOf<UByte>()
    while (number != 0UL) {
        bytes.add((number % 256UL).toUByte())
        number /= 256UL
    }
    return UByteArray(l - bytes.size) { 0u } + bytes.reversed()
}

fun Int.serial() = numberSerialise(this.toUInt().toULong(), 4)

internal object InstantEpochSecondsSerializer : KSerializer<Instant> {
    override fun deserialize(decoder: Decoder): Instant =
        Instant.fromEpochSeconds(decoder.decodeLong())

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Instant", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeLong(value.epochSeconds)
    }
}