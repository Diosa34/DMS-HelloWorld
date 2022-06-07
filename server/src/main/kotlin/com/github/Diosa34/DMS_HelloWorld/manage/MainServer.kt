package com.github.Diosa34.DMS_HelloWorld.manage

import com.github.Diosa34.DMS_HelloWorld.sql.SQLAndMemoryCollection
import com.github.Diosa34.DMS_HelloWorld.sql.SQLManager
import com.github.Diosa34.DMS_HelloWorld.sql.SQLUsersCollection
import org.postgresql.util.PSQLException
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    try {
        val host = InetAddress.getLocalHost()
        val port = 5489

        val collection = SQLAndMemoryCollection()
        val usersCollection = SQLUsersCollection()

        val log = Logger.getLogger("ServerLogger")
        try {
//            LogManager.getLogManager().readConfiguration(FileInputStream("./servLog.properties"))
            LogManager.getLogManager().readConfiguration(FileInputStream("C:\\Users\\Diosa\\IdeaProjects\\" +
                    "Laboratory5\\server\\src\\main\\resources\\servLog.properties"))
            log.info("Начало работы серверного приложения")

        } catch (e: IOException) {
            println("Could not setup logger configuration: $e")
        } catch (ex: ClassNotFoundException) {
            println("Logger not configured")
        } catch (ex: FileNotFoundException){
            println("Config file not found")
        }

        try {
            SQLManager.main(collection, log)
        } catch (ex: PSQLException) {
            log.log(Level.WARNING, "PSQLException ", ex)
        } catch (ex: FileNotFoundException) {
            log.log(Level.WARNING, "FileNotFoundException: ", ex)
        }

        ServerManager.manager(host, port, collection, usersCollection, log)
    } catch (ex: FileNotFoundException) {
        println("Конфигурационные файлы не найдены")
        return
    } catch (ex: UnknownHostException) {
        println(ex.message)
        return
    }
}

