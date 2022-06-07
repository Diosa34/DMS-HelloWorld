//package com.github.Diosa34.DMS_HelloWorld.serialize
//
//import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
//import com.github.Diosa34.DMS_HelloWorld.exceptions.DeserializeException
//import kotlinx.serialization.DeserializationStrategy
//import kotlinx.serialization.ExperimentalSerializationApi
//import kotlinx.serialization.SerializationException
//import kotlinx.serialization.descriptors.SerialDescriptor
//import kotlinx.serialization.descriptors.elementNames
//import kotlinx.serialization.encoding.CompositeDecoder
//import kotlinx.serialization.encoding.Decoder
//import kotlinx.serialization.modules.EmptySerializersModule
//import kotlinx.serialization.modules.SerializersModule
//
//class IteratorByte(
//    private val iterator: Iterator<UByte>,
//    private var size: Int
//): Iterator<UByte> {
//    override fun hasNext(): Boolean {
//        return if (this.size > 0) {
//            return if (this.iterator.hasNext()) {
//                this.iterator.hasNext()
//            } else {
//                throw DeserializeException("Нет доступных байтов для чтения")
//            }
//        } else {
//            false
//        }
//    }
//
//    override fun next(): UByte {
//        if (size <= 0) {
//            throw IllegalStateException("Несанкционированный доступ к дополнительным байтам")
//        }
//        this.size--
//        return this.iterator.next()
//    }
//}
//
//fun Iterator<UByte>.deserializeNumber(size: Int): ULong {
//    var number: ULong = 0U
//    for (i in IteratorByte(this, size)){
//        number = number * 256U + i.toULong()
//    }
//    return number
//}
//
//fun Iterator<UByte>.deserializeInt(): Int {
//    return this.deserializeNumber(4).toUInt().toInt()
//}
//
//fun Iterator<UByte>.deserializeString(): String {
//    val size: Int = this.deserializeInt()
//    return IteratorByte(this, size).asSequence().toList().toUByteArray().toByteArray().toString(Charsets.UTF_8)
//}
//
//class GeneralServerDecoder(
//    private val arr: ByteArray,
//    private val attempts: Int = 1,
//    private val logger: Logger,
//    private val iterator: Iterator<UByte>
//): Decoder {
//    override val serializersModule: SerializersModule
//        get() = EmptySerializersModule
//
//    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
//        return CompositeServerDecoderDelegate(this.arr, this.logger, this.attempts, this.iterator)
//    }
//
//    override fun decodeBoolean(): Boolean {
//        return false
//    }
//
//    override fun decodeByte(): Byte {
//        throw SerializationException()
//    }
//
//    override fun decodeChar(): Char {
//        throw SerializationException()
//    }
//
//    override fun decodeDouble(): Double {
//        throw SerializationException()
//    }
//
//    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
//        val result =  enumDescriptor.elementNames.indexOf(decodeString())
//        if (result == -1) throw SerializationException()
//        return result
//    }
//
//    override fun decodeFloat(): Float {
//        return Float.fromBits(this.decodeInt())
//    }
//
//    @ExperimentalSerializationApi
//    override fun decodeInline(inlineDescriptor: SerialDescriptor): Decoder {
//        throw SerializationException()
//    }
//
//    override fun decodeInt(): Int {
//        return this.iterator.deserializeNumber(4).toUInt().toInt()
//    }
//
//    override fun decodeLong(): Long {
//        return this.iterator.deserializeNumber(8).toLong()
//    }
//
//    @ExperimentalSerializationApi
//    override fun decodeNotNullMark(): Boolean {
//        return false
//    }
//
//    @ExperimentalSerializationApi
//    override fun decodeNull(): Nothing? {
//        return null
//    }
//
//    override fun decodeShort(): Short {
//        throw SerializationException()
//    }
//
//    override fun decodeString(): String {
//        val size: Int = this.decodeInt()
//        return IteratorByte(this.iterator, size).asSequence().toList().toUByteArray().toByteArray().toString(Charsets.UTF_8)
//    }
//}
//
//class CompositeServerDecoderDelegate(
//    private val arr: ByteArray,
//    private val logger: Logger,
//    private val attempts: Int,
//    private val iterator: Iterator<UByte>
//) : CompositeDecoder {
//
//    private var index: Int = 0
//
//    override val serializersModule: SerializersModule
//        get() = EmptySerializersModule
//
//    override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
//        throw SerializationException()
//    }
//
//    override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
//        throw SerializationException()
//    }
//
//    override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
//        throw SerializationException()
//    }
//
//    override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
//        throw SerializationException()
//    }
//
//    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
//        if (this.index >= descriptor.elementsCount) return CompositeDecoder.DECODE_DONE
//        return this.index++
//    }
//
//    override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
//        return Float.fromBits(this.iterator.deserializeInt())
//    }
//
//    @ExperimentalSerializationApi
//    override fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder {
//        throw SerializationException()
//    }
//
//    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
//        val result =  descriptor.elementNames.indexOf(this.iterator.deserializeString())
//        if (result == -1) throw SerializationException()
//        return result
//    }
//
//    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
//        return this.iterator.deserializeNumber(8).toLong()
//    }
//
//    @ExperimentalSerializationApi
//    override fun <T : Any> decodeNullableSerializableElement(
//        descriptor: SerialDescriptor,
//        index: Int,
//        deserializer: DeserializationStrategy<T?>,
//        previousValue: T?
//    ): T? {
//        return deserializer.deserialize(GeneralServerDecoder(this.arr, this.attempts, this.logger, this.iterator))
//    }
//
//    override fun <T> decodeSerializableElement(
//        descriptor: SerialDescriptor,
//        index: Int,
//        deserializer: DeserializationStrategy<T>,
//        previousValue: T?
//    ): T {
//        return deserializer.deserialize(GeneralServerDecoder(this.arr, this.attempts, this.logger, this.iterator))}
//
//    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
//        throw SerializationException()
//    }
//
//    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
//        return this.iterator.deserializeString()
//    }
//
//    override fun endStructure(descriptor: SerialDescriptor) {
//        throw SerializationException()
//    }
//}
//
