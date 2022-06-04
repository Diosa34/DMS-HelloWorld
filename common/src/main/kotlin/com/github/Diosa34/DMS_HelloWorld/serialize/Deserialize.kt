//@file:OptIn(ExperimentalUnsignedTypes::class)
//
//package com.github.Diosa34.DMS_HelloWorld.serialize
//
//import com.github.Diosa34.DMS_HelloWorld.collection.FuelType
//import com.github.Diosa34.DMS_HelloWorld.collection.VehicleType
//import com.github.Diosa34.DMS_HelloWorld.exceptions.DeserializeException
//import java.time.Instant
//import java.time.ZoneId
//import java.time.ZonedDateTime
//
//fun Iterator<UByte>.deserializeLong(): Long {
//    return this.deserializeNumber(8).toLong()
//}
//
//fun Iterator<UByte>.deserializeFloat(): Float {
//    return Float.fromBits(this.deserializeInt())
//}
//
//
//
//fun Iterator<UByte>.deserializeCoordinates(): Coordinates {
//    val x: Float = this.deserializeFloat()
//    val y: Int = this.deserializeInt()
//    return Coordinates(x, y)
//}
//
//fun Iterator<UByte>.deserializeVehicleType(): VehicleType {
//    return VehicleType.stringToType[this.deserializeString()]!!
//}
//
//fun Iterator<UByte>.deserializeVehicle(): Vehicle {
//    val name: String = this.deserializeString()
//    val coordinates: Coordinates = this.deserializeCoordinates()
//    val m: Long = this.deserializeLong()
//    val creationDate: ZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(m), ZoneId.systemDefault())
//    val enginePower: Float = this.deserializeFloat()
//    val vehicleType: VehicleType = this.deserializeVehicleType()
//    val fuelType: FuelType = FuelType.stringToType[this.deserializeString()]!!
//    val username: String = this.deserializeString()
//    // добавить проверку типов
//    return Vehicle(name, coordinates, enginePower, vehicleType, fuelType, username)
//}