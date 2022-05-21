package com.github.Diosa34.DMS_HelloWorld

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.FileInputStream
import java.io.IOException
import java.util.*


object SQLManager {
    fun main(collection: SQLAndMemoryCollection) {
        val property = Properties()

        val cFile = "C:\\Users\\Diosa\\IdeaProjects\\Laboratory5\\server\\src\\main\\resources\\config.properties"
        val fis = FileInputStream(cFile)
        property.load(fis)
        val host = property.getProperty("db.host")
        val driver = property.getProperty("db.driver")
        val user = property.getProperty("db.user")
        val password = property.getProperty("db.password")

        Database.connect(host, driver, user, password)

        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(SQLVehicles)
            SchemaUtils.create(InformationTable)

            for (i in collection.getSqlCollection()){
                collection.getCollectionInMemory().add(i)
            }
            println("База данных и коллекция в памяти синхронизированы")
        }
    }
}

//        Database.connect( " jdbc:postgresql://localhost:5432/Cities " , driver =  " org.postgresql.Driver " ,
//            user =  " Diosa " , password =  " 3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2# " )
