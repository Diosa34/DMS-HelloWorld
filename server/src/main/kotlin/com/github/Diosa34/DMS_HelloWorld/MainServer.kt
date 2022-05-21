package com.github.Diosa34.DMS_HelloWorld

import org.postgresql.util.PSQLException
import java.io.FileNotFoundException
import java.net.InetAddress

fun main() {
    val host = InetAddress.getLocalHost()
    val port = 6789
    val collection = SQLAndMemoryCollection()
    try {
        SQLManager.main(collection)
    } catch(ex: PSQLException) {
        println("Проблемы подключения к базе данных")
    } catch (ex: FileNotFoundException) {
        println("Файл свойств не найден")
    }

    ServerManager.manager(host, port, collection)
}
