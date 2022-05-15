package com.github.Diosa34.DMS_HelloWorld

import org.jetbrains.exposed.sql.*

interface SQLCommand: AbstractCommand {
    override fun add(vehicle: Vehicle) {
        SQLVehicles.insert {
            it[id] = vehicle.id
            it[name] = vehicle.name
            it[x] = vehicle.coordinates.x
            it[y] = vehicle.coordinates.y
            it[creationDate] = vehicle.creationDate.toLocalDateTime()
            it[enginePower] = vehicle.enginePower
            it[vehicleType] = vehicle.vehicleType
            it[fuelType] = vehicle.fuelType
        }
    }

    override fun clear() {
        SQLVehicles.deleteAll()
    }

    override fun remove(vehicle: Vehicle) {
        SQLVehicles.deleteWhere (null, null) { SQLVehicles.id eq vehicle.id }
    }

    override fun update(vehicle: Vehicle) {
        SQLVehicles.update({ SQLVehicles.id eq vehicle.id }) {
            it[name] = vehicle.name
            it[x] = vehicle.coordinates.x
            it[y] = vehicle.coordinates.y
            it[creationDate] = vehicle.creationDate.toLocalDateTime()
            it[enginePower] = vehicle.enginePower
            it[vehicleType] = vehicle.vehicleType
            it[fuelType] = vehicle.fuelType }
    }
}