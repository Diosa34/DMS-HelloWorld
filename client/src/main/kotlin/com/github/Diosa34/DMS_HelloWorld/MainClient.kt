package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
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
    val port = 5489

    val logger: Logger = ConsoleLogger
    val client: Client

    val log: java.util.logging.Logger = java.util.logging.Logger.getLogger("ClientLogger")
    try {
        LogManager.getLogManager().readConfiguration(
//            FileInputStream("./clientLog.properties")
              FileInputStream("C:\\Users\\Diosa\\IdeaProjects\\Laboratory5\\client\\src\\main\\resources" +
                      "\\clientLog.properties")
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

    logger.print("Для начала работы введите команду 'registry' для регистрации или 'log_in', если вы уже зарегистрированы")
//    logger.print("Информация о командах доступна по команде 'help'")

    RequestManager.manage(logger, 3, ConsoleStringReader, client)
}