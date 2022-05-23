package com.github.Diosa34.DMS_HelloWorld

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.ZoneOffset

class SQLCollection: CollectionOfVehicles {
    override fun print(){
        TODO()
    }
    override fun add(vehicle: Vehicle) {
        transaction { SQLVehicles.insert (vehicle.sqlClosure) }
    }

    override fun addIfMin(name: String, vehicle: Vehicle): CollectionOfVehicles.AddIfMinResult {
        return if (transaction { SQLVehicles.select { SQLStringLen(SQLVehicles.name) lessEq name.length}.empty() }){
            transaction { SQLVehicles.insert (vehicle.sqlClosure) }
            CollectionOfVehicles.AddIfMinResult.SUCCESS
        } else {
            CollectionOfVehicles.AddIfMinResult.LESS_FOUND
        }
    }

    override fun clear() {
        transaction { SQLVehicles.deleteAll() }
    }

    override fun countByType(type: VehicleType): Int {
        return transaction { SQLVehicles.select { SQLVehicles.vehicleType eq type } }.count().toInt()
    }

    override fun groupCountingByType(): Groups {
        return Groups(
            transaction { SQLVehicles.select { SQLVehicles.vehicleType eq VehicleType.CAR } }.count().toInt(),
            transaction { SQLVehicles.select {SQLVehicles.vehicleType eq VehicleType.SUBMARINE } }.count().toInt(),
            transaction { SQLVehicles.select {SQLVehicles.vehicleType eq VehicleType.SHIP } }.count().toInt())
    }

    override fun info(): CollectionOfVehicles.Information {
        return CollectionOfVehicles.Information(
            transaction { SQLVehicles.selectAll().count().toInt()},
            transaction { InformationTable.selectAll().map {it[InformationTable.initDate]}} [0])
    }

    override fun removeById(id: Int): CollectionOfVehicles.RemoveByIdResult {
        val deletedCount = transaction { SQLVehicles.deleteWhere {SQLVehicles.id eq id } }
        return if (deletedCount > 0) {
            CollectionOfVehicles.RemoveByIdResult.DELETED
        } else {
            CollectionOfVehicles.RemoveByIdResult.NOT_FOUND
        }
    }

    override fun removeFirst(): Boolean {
        val minId = SQLVehicles.id.min()
        val id = transaction { SQLVehicles.slice(minId).selectAll().map{SQLVehicles.id}.first() }
        val number = transaction {
            SQLVehicles.deleteWhere{SQLVehicles.id eq id } }
        return number > 0
    }

    override fun removeLower(name: String): CollectionOfVehicles.RemoveLowerResult {
        val number = transaction { SQLVehicles.deleteWhere { SQLStringLen(SQLVehicles.name) less name.length } }
        return if (number > 0) {
            CollectionOfVehicles.RemoveLowerResult.DELETED
        } else {
            CollectionOfVehicles.RemoveLowerResult.LESS_NOT_FOUND
        }
    }

    // возможна ошибка транзакции внутри лямбды
    override fun iterator(): Iterator<Vehicle> {
        return transaction { SQLVehicles.selectAll().map{ r ->
            SQLVehicles.run{ Vehicle(r[id], r[name], Coordinates(r[x], r[y]),
                r[creationDate].atZone(ZoneOffset.UTC), r[enginePower], r[vehicleType], r[fuelType]) }}.iterator() }
    }

    override fun sumOfEnginePower(): Float {
        return transaction { SQLVehicles.selectAll().map{it[SQLVehicles.enginePower]}.sum() }
    }

    override fun update(id: Int, vehicle: Vehicle): CollectionOfVehicles.UpdateResult {
        return if (transaction { SQLVehicles.update({ SQLVehicles.id eq id }, body = vehicle.sqlClosure) > 0 }) {
            CollectionOfVehicles.UpdateResult.UPDATED
        } else {
            CollectionOfVehicles.UpdateResult.NOT_FOUND
        }
    }


    fun selectMaxId(): Int? {
        val maxId = SQLVehicles.id.max()
        return transaction { SQLVehicles.slice(maxId).selectAll().map{it[maxId]}.last() }
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
