package com.github.Diosa34.DMS_HelloWorld

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import java.time.ZoneOffset

open class SQLCollection: CollectionOfVehicles {
    override fun add(vehicle: Vehicle) {
        SQLVehicles.insert (vehicle.sqlClosure)
    }

    override fun addIfMin(name: String, vehicle: Vehicle): CollectionOfVehicles.AddIfMinResult {
        return if (SQLVehicles.select { SQLStringLen(SQLVehicles.name) lessEq name.length}.empty()){
            SQLVehicles.insert (vehicle.sqlClosure)
            CollectionOfVehicles.AddIfMinResult.SUCCESS
        } else {
            CollectionOfVehicles.AddIfMinResult.LESS_FOUND
        }
    }

    override fun clear() {
        SQLVehicles.deleteAll()
    }

    override fun countByType(type: VehicleType): Int {
        return SQLVehicles.select { SQLVehicles.vehicleType eq type }.count().toInt()
    }

    override fun groupCountingByType(): Groups {
        return Groups(SQLVehicles.select { SQLVehicles.vehicleType eq VehicleType.CAR }.count().toInt(),
            SQLVehicles.select {SQLVehicles.vehicleType eq VehicleType.SUBMARINE }.count().toInt(),
            SQLVehicles.select {SQLVehicles.vehicleType eq VehicleType.SHIP }.count().toInt())
    }

    override fun info(): CollectionOfVehicles.Information {
        return CollectionOfVehicles.Information(SQLVehicles.selectAll().count().toInt(),
            InformationTable.selectAll().map {it[InformationTable.initDate]}[0])
    }

    override fun removeById(id: Int): CollectionOfVehicles.RemoveByIdResult {
        val deletedCount = SQLVehicles.deleteWhere {SQLVehicles.id eq id }
        return if (deletedCount > 0) {
            CollectionOfVehicles.RemoveByIdResult.DELETED
        } else {
            CollectionOfVehicles.RemoveByIdResult.NOT_FOUND
        }
    }

    override fun removeFirst(): Boolean {
        val number = SQLVehicles.deleteWhere(1){TrueBuilder}
        return number > 0
    }

    override fun removeLower(name: String): CollectionOfVehicles.RemoveLowerResult {
        val number = SQLVehicles.deleteWhere { SQLStringLen(SQLVehicles.name) less name.length }
        return if (number > 0) {
            CollectionOfVehicles.RemoveLowerResult.DELETED
        } else {
            CollectionOfVehicles.RemoveLowerResult.LESS_NOT_FOUND
        }
    }

    override fun iterator(): Iterator<Vehicle> {
        return SQLVehicles.selectAll().map{ r ->
            SQLVehicles.run{ Vehicle(r[this.id], r[this.name], Coordinates(r[this.x], r[this.y]),
                r[this.creationDate].atZone(ZoneOffset.UTC), r[this.enginePower], r[this.vehicleType], r[this.fuelType]) }}.iterator()
    }

    override fun sumOfEnginePower(): Float {
        return SQLVehicles.selectAll().map{it[SQLVehicles.enginePower]}.sum()
    }

    override fun update(id: Int, vehicle: Vehicle): CollectionOfVehicles.UpdateResult {
        return if (SQLVehicles.update({ SQLVehicles.id eq vehicle.id }, body = vehicle.sqlClosure) > 0) {
            CollectionOfVehicles.UpdateResult.UPDATED
        } else {
            CollectionOfVehicles.UpdateResult.NOT_FOUND
        }
    }

    private val Vehicle.sqlClosure: SQLVehicles.(UpdateBuilder<*>) -> Unit
        get() = {
            it[name] = this@sqlClosure.name
            it[x] = this@sqlClosure.coordinates.x
            it[y] = this@sqlClosure.coordinates.y
            it[creationDate] = this@sqlClosure.creationDate.toLocalDateTime()
            it[enginePower] = this@sqlClosure.enginePower
            it[vehicleType] = this@sqlClosure.vehicleType
            it[fuelType] = this@sqlClosure.fuelType
        }
}
