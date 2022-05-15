package com.github.Diosa34.DMS_HelloWorld

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class SQLManager {
    fun main(args: Array<String>) {
        //an example connection to H2 DB
        Database.connect("jdbc:h2:mem:Cities", driver = "org.h2.Driver")

        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(SQLVehicles)
        }
    }
}