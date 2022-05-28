package com.github.Diosa34.DMS_HelloWorld

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.FileInputStream
import java.util.*
import java.util.logging.Logger


object SQLManager {
    fun main(collection: SQLAndMemoryCollection, log: Logger) {
        val property = Properties()

        val cFile = "./config.properties"
        val fis = FileInputStream(cFile)
        property.load(fis)
        val host = property.getProperty("db.host")
        val driver = property.getProperty("db.driver")
        val user = property.getProperty("db.user")
        val password = property.getProperty("db.password")

        Database.connect(host, driver, user, password)

        transaction {
            SchemaUtils.create(SQLVehicles)
            SchemaUtils.create(InformationTable)

            for (i in collection.getSqlCollection()){
                collection.getCollectionInMemory().add(i)
            }
            log.info("База данных и коллекция в памяти синхронизированы")
        }
    }
}
