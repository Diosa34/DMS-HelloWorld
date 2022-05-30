package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.collection.InstanceCreator
import com.github.Diosa34.DMS_HelloWorld.io.ConsoleLogger
import com.github.Diosa34.DMS_HelloWorld.io.ConsoleStringReader
import java.io.FileInputStream
import java.io.IOException
import java.net.ConnectException
import java.net.InetAddress
import java.util.logging.Level
import java.util.logging.LogManager

fun main() {
    val host = InetAddress.getLocalHost()
    val port = 5894

    val logger: Logger = ConsoleLogger
    val client: Client

    val log: java.util.logging.Logger = java.util.logging.Logger.getLogger("ClientLogger")
    try {
        LogManager.getLogManager().readConfiguration(
            FileInputStream("./clientLog.properties")
        )
        log.info("Начало работы клиентского приложения")

    } catch (e: IOException) {
        println("Could not setup logger configuration: $e")
    } catch (ex: ClassNotFoundException) {
        println("Logger not configured")
    }

    try {
        client = Client(host, port, log)
    } catch (ex: ConnectException) {
        log.log(Level.SEVERE, "ConnectException: ", ex)
        logger.print("Соединение не установлено, запустите сервер, а затем клиент.")
        return
    }

//    logger.print("Информация о командах доступна по команде 'help'")

    val authentication: ArrayList<String>

    RequestManager.manage(logger, 3, InstanceCreator.CREATE_WITH_INPUT, ConsoleStringReader, client, log)
}