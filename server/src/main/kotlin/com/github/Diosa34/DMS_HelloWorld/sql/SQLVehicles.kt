package com.github.Diosa34.DMS_HelloWorld.sql

import com.github.Diosa34.DMS_HelloWorld.collection.FuelType
import com.github.Diosa34.DMS_HelloWorld.collection.VehicleType
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import kotlinx.datetime.Instant
import kotlinx.datetime.Clock.System
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object SQLVehicles: Table() {
    var id: Column<Int> = integer("id").uniqueIndex().autoIncrement().check { it greater 0 }//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    var name: Column<String> = varchar("name", 50).check { it neq "" }//Поле не может быть null, Строка не может быть пустой
    var x: Column<Float> = float("x") //Поле не может быть null
    var y: Column<Int> = integer("y") //Поле не может быть null
    var creationDate: Column<Instant> = timestamp("creationDate").clientDefault{System.now()} //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    var enginePower: Column<Float> = float("enginePower").check { it greater 0 } //Поле не может быть null, Значение поля должно быть больше 0
    var vehicleType: Column<VehicleType> = enumeration("vehicleType") //Поле не может быть null
    var fuelType: Column<FuelType> = enumeration("fuelType") //Поле может быть null
    val username: Column<String> = varchar("username", 50).check { it neq "" }
}

object InformationTable: Table() {
    val initDate: Column<Instant> = timestamp("initDate")
}

object Users: Table() {
    val login: Column<String> = varchar("user", 50).uniqueIndex().check { it neq "" }
    val password: Column<String> = varchar("password", 50).uniqueIndex().check { it neq "" }
}