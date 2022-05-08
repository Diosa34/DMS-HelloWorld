@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.github.Diosa34.DMS_HelloWorld

import java.util.*

fun String.serialize(): ByteArray {
    val str: ByteArray = this.toByteArray(Charsets.UTF_8)
    return str.size.serialize() + str
}

@Suppress("NOTHING_TO_INLINE")
private inline fun numberSerialise(number: ULong, l: Int): ByteArray {
    @Suppress("NAME_SHADOWING")
    var number = number
    val bytes = mutableListOf<Byte>()
    while (number != 0UL) {
        bytes.add((number % 256UL).toByte())
        number /= 256UL
    }
    return ByteArray(l - bytes.size) { 0 } + bytes.reversed()
}

fun Int.serialize() = numberSerialise(this.toULong(), 4)

fun Long.serialize() = numberSerialise(this.toULong(), 8)

fun Float.serialize(): ByteArray = this.toRawBits().serialize()

fun Vehicle.serialize(): ByteArray {
    var bytes: ByteArray = this.id.serialize()
    bytes += this.name.serialize()
    bytes += this.coordinates.serialize()
    bytes += Date.from(this.creationDate.toInstant()).time.serialize()
    bytes += this.enginePower.serialize()
    bytes += this.vehicleType.serialize()
    bytes += this.fuelType.serialize()
    return bytes
}

fun Coordinates.serialize(): ByteArray {
    var bytes: ByteArray = this.x.serialize()
    bytes += this.y.serialize()
    return bytes
}