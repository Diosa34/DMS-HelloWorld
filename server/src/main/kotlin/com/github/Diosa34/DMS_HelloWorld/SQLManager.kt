package com.github.Diosa34.DMS_HelloWorld

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object SQLManager {
    fun main() {
        //an example connection to  DB
        Database.connect("jdbc:h2:mem:SQLVehicles", "org.h2.Driver")
//        Database.connect( " jdbc:postgresql://localhost:5432/Cities " , driver =  " org.postgresql.Driver " ,
//            user =  " Diosa " , password =  " 3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e" +
//                    "3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2# " )

        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(SQLVehicles)
        }
    }
}