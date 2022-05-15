package com.github.Diosa34.DMS_HelloWorld

import java.net.ConnectException
import java.net.InetAddress

fun main() {
    CollectionOfVehicles()
    val logger: Logger = ConsoleLogger
    val host: InetAddress = InetAddress.getLocalHost()
    val port = 6789
    val client: Client
    try {
        client = Client(host, port)
    } catch (ex: ConnectException) {
        logger.print("Соединение не установлено, запустите сервер, а затем клиент.")
        return
    }

    logger.print("Информация о командах доступна по команде 'help'")

    RequestManager.manage(logger, 3, InstanceCreator.CREATE_WITH_INPUT, ConsoleStringReader, client)
}