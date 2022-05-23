package com.github.Diosa34.DMS_HelloWorld

import java.io.FileInputStream
import java.io.IOException
import java.net.ConnectException
import java.net.InetAddress
import java.util.*
import java.util.logging.Level
import java.util.logging.LogManager

fun main() {
    val property = Properties()
    val cFile = "client.properties"
    val fis = FileInputStream(cFile)
    property.load(fis)


    val host = property.getProperty("cl.host").map{InetAddress.getByName(it.toString()) ?:
    throw IOException("Хост некорректен")}[0]
    val port = property.getProperty("cl.port").toIntOrNull() ?: throw IOException("Порт не получен")

    val logger: Logger = ConsoleLogger
    val client: Client

    val log: java.util.logging.Logger = java.util.logging.Logger.getLogger("ClientLogger")
    try {
        LogManager.getLogManager().readConfiguration(
            FileInputStream("C:\\Users\\Diosa\\IdeaProjects" +
                "\\Laboratory5\\client\\src\\main\\resources\\logging.properties")
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

    logger.print("Информация о командах доступна по команде 'help'")

    RequestManager.manage(logger, 3, InstanceCreator.CREATE_WITH_INPUT, ConsoleStringReader, client, log)
}