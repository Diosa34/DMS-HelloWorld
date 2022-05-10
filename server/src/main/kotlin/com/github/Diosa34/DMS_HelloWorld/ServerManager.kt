package com.github.Diosa34.DMS_HelloWorld

import java.net.ConnectException
import java.net.InetAddress
import java.net.SocketException

object ServerManager {
    fun manager(host: InetAddress, port: Int) {
        while (true) {
            val server = Server(host, port)
            try {
                while (true) {
                    server.receive()
                }
            } catch (ex: ConnectException) {
                println(ex.message)
            } catch (ex: SocketException) {
                println("Соединение не установлено, запустите сервер, а затем клиент.")
            }
        }

    }
}