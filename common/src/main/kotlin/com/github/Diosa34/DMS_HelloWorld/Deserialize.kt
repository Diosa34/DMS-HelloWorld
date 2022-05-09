package com.github.Diosa34.DMS_HelloWorld

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class IteratorByte(
    private val iterator: Iterator<Byte>,
    private var size: Int
): Iterator<Byte> {
    override fun hasNext(): Boolean {
        return if (this.size > 0) {
            return if (this.iterator.hasNext()) {
                this.iterator.hasNext()
            } else {
                throw DeserializeException("Нет доступных байтов для чтения")
            }
        } else {
            false
        }
    }

    override fun next(): Byte {
        if (size <= 0) {
            throw IllegalStateException("Несанкционированный доступ к дополнительным байтам")
        }
        this.size--
        return this.iterator.next()
    }
}

fun Iterator<Byte>.deserializeNumber(size: Int): ULong {
    var number: ULong = 0U
    for (i in IteratorByte(this, size)){
        number += number * 256U + i.toULong()
    }
    return number
}

fun Iterator<Byte>.deserializeInt(): Int {
    return this.deserializeNumber(4).toInt()
}

fun Iterator<Byte>.deserializeLong(): Long {
    return this.deserializeNumber(8).toLong()
}

fun Iterator<Byte>.deserializeFloat(): Float {
    return Float.fromBits(this.deserializeInt())
}

fun Iterator<Byte>.deserializeString(): String {
    val size: Int = this.deserializeInt()
    return IteratorByte(this, size).asSequence().toList().toByteArray().toString(Charsets.UTF_8)
}

fun Iterator<Byte>.deserializeCoordinates(): Coordinates {
    val x: Float = this.deserializeFloat()
    val y: Int = this.deserializeInt()
    return Coordinates(x, y)
}

fun Iterator<Byte>.deserializeVehicleType(): VehicleType {
    return VehicleType.stringToType[this.deserializeString()]!!
}

fun Iterator<Byte>.deserializeVehicle(): Vehicle {
    val id: Int = this.deserializeInt()
    val name: String = this.deserializeString()
    val coordinates: Coordinates = this.deserializeCoordinates()
    val m: Long = this.deserializeLong()
    val creationDate: ZonedDateTime = ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(m),
        ZoneId.systemDefault())
    val enginePower: Float = this.deserializeFloat()
    val vehicleType: VehicleType = this.deserializeVehicleType()
    val fuelType: FuelType = FuelType.stringToType[this.deserializeString()]!!
    // добавить проверку типов
    return Vehicle(id, name, coordinates, creationDate, enginePower, vehicleType, fuelType)

}