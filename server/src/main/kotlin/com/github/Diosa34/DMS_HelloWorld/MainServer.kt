package com.github.Diosa34.DMS_HelloWorld

import org.postgresql.util.PSQLException
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.System.getenv
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    try {
//        val property = Properties()
//        val cFile = "server\\src\\main\\resources\\server.properties"
//        val fis = FileInputStream(cFile)
//        property.load(fis)


        val host = InetAddress.getLocalHost()
        val port = 5894

        val collection = SQLAndMemoryCollection()

        val log = Logger.getLogger("ServerLogger")
        try {
            LogManager.getLogManager().readConfiguration(FileInputStream("./servLog.properties"))
            log.info("Начало работы серверного приложения")

        } catch (e: IOException) {
            println("Could not setup logger configuration: $e")
        } catch (ex: ClassNotFoundException) {
            println("Logger not configured")
        }

        try {
            SQLManager.main(collection, log)
        } catch (ex: PSQLException) {
            log.log(Level.WARNING, "PSQLException ", ex)
        } catch (ex: FileNotFoundException) {
            log.log(Level.WARNING, "FileNotFoundException: ", ex)
        }

        ServerManager.manager(host, port, collection, log)
    } catch (ex: FileNotFoundException) {
        println("Конфигурационные файлы не найдены")
        return
    } catch (ex: UnknownHostException) {
        println(ex.message)
        return
    }
}

