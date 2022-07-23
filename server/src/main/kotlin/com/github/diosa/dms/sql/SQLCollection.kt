package com.github.diosa.dms.sql

import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.collection.Coordinates
import com.github.diosa.dms.collection.Groups
import com.github.diosa.dms.collection.Vehicle
import com.github.diosa.dms.collection.VehicleType
import com.github.diosa.dms.users.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.transactions.transaction

class SQLCollection: CollectionOfVehicles {
    @Synchronized
    override fun add(vehicle: Vehicle, user: User): Int {
        return transaction { SQLVehicles.insert (vehicle.sqlClosure(user)) get SQLVehicles.id }
    }

    @Synchronized
    override fun addIfMin(name: String, vehicle: Vehicle, user: User): Pair<CollectionOfVehicles.AddIfMinResult, Int?> {
        return if (transaction { SQLVehicles.select { SQLStringLen(SQLVehicles.name) lessEq name.length}.empty() }){
            Pair(CollectionOfVehicles.AddIfMinResult.SUCCESS, add(vehicle, user))
        } else {
            Pair(CollectionOfVehicles.AddIfMinResult.LESS_FOUND, null)
        }
    }

    @Synchronized
    override fun clear(user: User): CollectionOfVehicles.ClearResult {
        return if (transaction { SQLVehicles.deleteWhere{ SQLVehicles.username eq user.login} } > 0) {
            CollectionOfVehicles.ClearResult.DELETED
        } else {
            CollectionOfVehicles.ClearResult.NOT_FOUND
        }
    }

    @Synchronized
    override fun countByType(type: VehicleType): Int {
        return transaction { SQLVehicles.select { SQLVehicles.vehicleType eq type } }.count().toInt()
    }

    @Synchronized
    override fun groupCountingByType(): Groups {
        return Groups(
            transaction { SQLVehicles.select { SQLVehicles.vehicleType eq VehicleType.CAR } }.count().toInt(),
            transaction { SQLVehicles.select { SQLVehicles.vehicleType eq VehicleType.SUBMARINE } }.count().toInt(),
            transaction { SQLVehicles.select { SQLVehicles.vehicleType eq VehicleType.SHIP } }.count().toInt())
    }

    @Synchronized
    override fun info(): CollectionOfVehicles.Information {
        return CollectionOfVehicles.Information(
            transaction { SQLVehicles.selectAll().count().toInt()},
            transaction { InformationTable.selectAll().map {it[InformationTable.initDate]}} [0])
    }

    @Synchronized
    override fun removeById(id: Int, user: User): CollectionOfVehicles.RemoveByIdResult {
        val deletedCount = transaction { SQLVehicles.deleteWhere { SQLVehicles.id eq id and (SQLVehicles.username eq user.login) } }
        return if (deletedCount > 0) {
            CollectionOfVehicles.RemoveByIdResult.DELETED
        } else {
            CollectionOfVehicles.RemoveByIdResult.NOT_FOUND
        }
    }

    @Synchronized
    override fun removeFirst(user: User): Boolean {
        val minId = SQLVehicles.id.min()
        val id = transaction { SQLVehicles.slice(minId).selectAll().map{ SQLVehicles.id }.first() }
        val number = transaction {
            SQLVehicles.deleteWhere{ SQLVehicles.id eq id and (SQLVehicles.username eq user.login) } }
        return number > 0
    }

    @Synchronized
    override fun removeLower(name: String, user: User): CollectionOfVehicles.RemoveLowerResult {
        val number = transaction { SQLVehicles.deleteWhere { SQLStringLen(SQLVehicles.name) less name.length and
                (SQLVehicles.username eq user.login)} }
        return if (number > 0) {
            CollectionOfVehicles.RemoveLowerResult.DELETED
        } else {
            CollectionOfVehicles.RemoveLowerResult.LESS_NOT_FOUND
        }
    }

    @Synchronized
    override fun iterator(): Iterator<Vehicle> {
        return transaction { SQLVehicles.selectAll().map{ r ->
            SQLVehicles.run{ Vehicle(
                r[id], r[name], Coordinates(r[x], r[y]),
                r[creationDate], r[enginePower], r[vehicleType], r[fuelType], r[username]) }}.iterator() }
    }

    @Synchronized
    override fun sumOfEnginePower(): Float {
        return transaction { SQLVehicles.selectAll().map{it[SQLVehicles.enginePower]}.sum() }
    }

    @Synchronized
    override fun update(id: Int, vehicle: Vehicle, user: User): CollectionOfVehicles.UpdateResult {
        return if (transaction { SQLVehicles.update({ SQLVehicles.id eq id and (SQLVehicles.username eq user.login)},
                body = vehicle.sqlClosure(user)) > 0 }) {
            CollectionOfVehicles.UpdateResult.UPDATED
        } else {
            CollectionOfVehicles.UpdateResult.NOT_FOUND
        }
    }

    @Synchronized
    private fun Vehicle.sqlClosure(user:User): SQLVehicles.(UpdateBuilder<*>) -> Unit {
        return {tableColumns ->
            tableColumns[this.name] = this@sqlClosure.name
            tableColumns[this.x] = this@sqlClosure.coordinates.x
            tableColumns[this.y] = this@sqlClosure.coordinates.y
            tableColumns[this.creationDate] = this@sqlClosure.creationDate
            tableColumns[this.enginePower] = this@sqlClosure.enginePower
            tableColumns[this.vehicleType] = this@sqlClosure.type
            tableColumns[this.fuelType] = this@sqlClosure.fuelType
            tableColumns[this.username] = transaction { Users.select{ Users.login eq user.login}.map{ row -> row[Users.login]}[0] }
        }
    }
}
