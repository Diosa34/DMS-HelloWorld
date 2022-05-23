package com.github.Diosa34.DMS_HelloWorld

import org.postgresql.util.PSQLException
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.System.getenv
import java.net.InetAddress
import java.util.*
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    val property = Properties()
    val cFile = "server.properties"
    val fis = FileInputStream(cFile)
    property.load(fis)


    val host = property.getProperty("srv.host").map{InetAddress.getByName(it.toString()) ?:
    throw IOException("Хост некорректен")}[0]
    val port = property.getProperty("srv.port").toIntOrNull() ?: throw IOException("Порт не получен")

    val collection = SQLAndMemoryCollection()

    val log = Logger.getLogger("ServerLogger")
    try {
        LogManager.getLogManager().readConfiguration(FileInputStream("logging.properties"))
        log.info("Начало работы серверного приложения")

    } catch (e: IOException) {
        println("Could not setup logger configuration: $e")
    } catch (ex: ClassNotFoundException) {
        println("Logger not configured")
    }

    try {
        SQLManager.main(collection, log)
    } catch(ex: PSQLException) {
        log.log(Level.WARNING, "PSQLException ", ex)
    } catch (ex: FileNotFoundException) {
        log.log(Level.WARNING, "FileNotFoundException: ", ex)
    }

    ServerManager.manager(host, port, collection, log)
}

