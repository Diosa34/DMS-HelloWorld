package com.github.Diosa34.DMS_HelloWorld.serialize

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractStringReader
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

/**
 * Verification the validity of an argument
 */
fun <T: Any> tryGet(logger: Logger, field: String, t: Int, message: String, number: String.() -> T?) : T? {
    for(i in 0 until t) {
        var data = field
        if(i != 0){
            data = readln()
        }
        data.number().let{ number ->
            if(number == null){
                logger.print("Данные некорректны")
                logger.print(message)
                if (i != t-1) {
                    logger.print("Количество оставшихся попыток: ${t-i-1}")
                }
            }
            else return@tryGet number
        }
    }
    return null
}

class GeneralDecoder(
    private val attempts: Int,
    private val stringReader: AbstractStringReader,
    private val logger: Logger,
    private val compositeDecoder: CompositeDecoder
): Decoder {
    override val serializersModule: SerializersModule
        get() = EmptySerializersModule

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        return compositeDecoder
    }

    override fun decodeBoolean(): Boolean {
        return false
    }

    override fun decodeByte(): Byte {
        throw SerializationException()
    }

    override fun decodeChar(): Char {
        throw SerializationException()
    }

    override fun decodeDouble(): Double {
        throw SerializationException()
    }

    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
        return tryGet(this.logger, this.stringReader.getNextLine(), attempts, "В качестве типа введите число от 0 до 2") {
            if (this.toIntOrNull() in 0 .. 2) {
                this.toIntOrNull()
            } else {null}
        } ?: throw SerializationException()
    }

    override fun decodeFloat(): Float {
        return tryGet(this.logger, this.stringReader.getNextLine(), attempts,
            "Значение должно быть числом в десятичных дробях через точку без лишних символов")
        { toFloatOrNull() } ?: throw SerializationException()
    }

    @ExperimentalSerializationApi
    override fun decodeInline(inlineDescriptor: SerialDescriptor): Decoder {
        throw SerializationException()
    }

    override fun decodeInt(): Int {
        return tryGet(this.logger, this.stringReader.getNextLine(), attempts,
            "Значение должно быть целым числом без лишних символов")
        { toIntOrNull() } ?: throw SerializationException()
    }

    override fun decodeLong(): Long {
        throw SerializationException()
    }

    @ExperimentalSerializationApi
    override fun decodeNotNullMark(): Boolean {
        return false
    }

    @ExperimentalSerializationApi
    override fun decodeNull(): Nothing? {
        return null
    }

    override fun decodeShort(): Short {
        throw SerializationException()
    }

    override fun decodeString(): String {
        return tryGet(this.logger, this.stringReader.getNextLine(), attempts, "Значение не может быть пустой строкой") {
            takeIf { isNotBlank() } } ?: throw SerializationException()
    }
}

class CompositeConsoleDecoderDelegate(
    private val logger: Logger,
    private val attempts: Int,
    private val stringReader: AbstractStringReader,
) : CompositeDecoder {

    private var index: Int = 0

    override val serializersModule: SerializersModule
        get() = EmptySerializersModule

    val stringToType: Map<String, String> = mapOf(
        "STRING" to "в виде строки",
        "INT" to "в виде целого числа",
        "FLOAT" to "в виде десятичной дроби через точку"
    )

    private fun logWithQuestion(descriptor: SerialDescriptor, index: Int) {
        logger.print("Введите значение поля ${descriptor.getElementName(index)} " +
                " ${stringToType[descriptor.getElementDescriptor(index).kind.toString()]}"
        )
    }

    override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
        throw SerializationException()
    }

    override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
        throw SerializationException()
    }

    override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
        throw SerializationException()
    }

    override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
        throw SerializationException()
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        if (this.index >= descriptor.elementsCount) return CompositeDecoder.DECODE_DONE
        return this.index++
    }

    override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
        logWithQuestion(descriptor, index)
        return tryGet(this.logger, this.stringReader.getNextLine(), attempts,
            "Значение должно быть числом в десятичных дробях через точку без лишних символов")
        { toFloatOrNull() } ?: throw SerializationException()
    }

    @ExperimentalSerializationApi
    override fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder {
        throw SerializationException()
    }

    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
        logWithQuestion(descriptor, index)
        return tryGet(this.logger, this.stringReader.getNextLine(), attempts,
            "Значение должно быть целым числом без лишних символов")
        { toIntOrNull() } ?: throw SerializationException()
    }

    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
        throw SerializationException()
    }

    @ExperimentalSerializationApi
    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>,
        previousValue: T?
    ): T? {
        logger.print("Ввод объекта: ${descriptor.getElementName(index)}"
        )
        return deserializer.deserialize(GeneralDecoder(this.attempts, this.stringReader, this.logger,
            CompositeConsoleDecoderDelegate(this.logger, this.attempts, this.stringReader)))
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T {
        logger.print("Ввод объекта: ${descriptor.getElementName(index)}"
        )
        return deserializer.deserialize(GeneralDecoder(this.attempts, this.stringReader, this.logger,
            CompositeConsoleDecoderDelegate(this.logger, this.attempts, this.stringReader)))
    }

    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
        throw SerializationException()
    }

    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
        return tryGet(this.logger, this.stringReader.getNextLine(), attempts,
            "Значение не может быть пустой строкой") {
            takeIf { isNotBlank() } } ?: throw SerializationException()
    }

    override fun endStructure(descriptor: SerialDescriptor) {
    }
}

class CompositeFileDecoderDelegate(
    private val logger: Logger,
    private val attempts: Int,
    private val stringReader: AbstractStringReader,
) : CompositeDecoder {

    private var index: Int = 0

    override val serializersModule: SerializersModule
        get() = EmptySerializersModule

    override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
        throw SerializationException()
    }

    override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
        throw SerializationException()
    }

    override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
        throw SerializationException()
    }

    override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
        throw SerializationException()
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        if (this.index >= descriptor.elementsCount) return CompositeDecoder.DECODE_DONE
        return this.index++
    }

    override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
        return tryGet(this.logger, this.stringReader.getNextLine(), attempts,
            "Значение должно быть числом в десятичных дробях через точку без лишних символов")
        { toFloatOrNull() } ?: throw SerializationException()
    }

    @ExperimentalSerializationApi
    override fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder {
        throw SerializationException()
    }

    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
        return tryGet(this.logger, this.stringReader.getNextLine(), attempts,
            "Значение должно быть целым числом без лишних символов")
        { toIntOrNull() } ?: throw SerializationException()
    }

    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
        throw SerializationException()
    }

    @ExperimentalSerializationApi
    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>,
        previousValue: T?
    ): T? {
        logger.print("Ввод объекта: ${descriptor.getElementName(index)}"
        )
        return deserializer.deserialize(GeneralDecoder(this.attempts, this.stringReader, this.logger,
            CompositeConsoleDecoderDelegate(this.logger, this.attempts, this.stringReader)))
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T {
        logger.print("Ввод объекта: ${descriptor.getElementName(index)}"
        )
        return deserializer.deserialize(GeneralDecoder(this.attempts, this.stringReader, this.logger,
            CompositeConsoleDecoderDelegate(this.logger, this.attempts, this.stringReader)))
    }

    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
        throw SerializationException()
    }

    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
        return tryGet(this.logger, this.stringReader.getNextLine(), attempts,
            "Значение не может быть пустой строкой") {
            takeIf { isNotBlank() } } ?: throw SerializationException()
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        throw SerializationException()
    }
}