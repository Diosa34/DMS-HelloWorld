package com.github.Diosa34.DMS_HelloWorld

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.time.ZonedDateTime

object SQLVehicles: Table() {
    var id: Column<Int> = integer("id").uniqueIndex().check { it greater 0 }//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    var name: Column<String> = varchar("name", 50).check { it neq "" }//Поле не может быть null, Строка не может быть пустой
    var x: Column<Float> = float("x") //Поле не может быть null
    var y: Column<Int> = integer("y") //Поле не может быть null
    var creationDate: Column<LocalDateTime> = datetime("creationDate").clientDefault {ZonedDateTime.now().toLocalDateTime()} //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    var enginePower: Column<Float> = float("enginePower").check { it greater 0 } //Поле не может быть null, Значение поля должно быть больше 0
    var vehicleType: Column<VehicleType> = enumeration("vehicleType") //Поле не может быть null
    var fuelType: Column<FuelType> = enumeration("fuelType") //Поле может быть null
}
