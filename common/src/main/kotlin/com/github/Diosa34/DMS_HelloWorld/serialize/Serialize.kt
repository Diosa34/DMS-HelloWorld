@file:Suppress("EXPERIMENTAL_API_USAGE", "OPT_IN_USAGE")

package com.github.Diosa34.DMS_HelloWorld.serialize

import com.github.Diosa34.DMS_HelloWorld.Coordinates
import com.github.Diosa34.DMS_HelloWorld.Vehicle
import java.util.*

fun String.serialize(): UByteArray {
    val str: UByteArray = this.toByteArray(Charsets.UTF_8).toUByteArray()
    return str.size.serialize() + str
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

fun Int.serialize() = numberSerialise(this.toUInt().toULong(), 4)

fun Long.serialize() = numberSerialise(this.toULong(), 8)

fun Float.serialize(): UByteArray = this.toRawBits().serialize()

fun Vehicle.serialize(): UByteArray {
    var bytes: UByteArray = this.name.serialize()
    bytes += this.coordinates.serialize()
    bytes += Date.from(this.creationDate.toInstant()).time.serialize()
    bytes += this.enginePower.serialize()
    bytes += this.vehicleType.serialize()
    bytes += this.fuelType.serialize()
    return bytes
}

fun Coordinates.serialize(): UByteArray {
    var bytes: UByteArray = this.x.serialize()
    bytes += this.y.serialize()
    return bytes
}