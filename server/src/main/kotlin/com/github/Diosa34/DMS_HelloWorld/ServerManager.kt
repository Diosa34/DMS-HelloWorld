package com.github.Diosa34.DMS_HelloWorld

import java.net.BindException
import java.net.ConnectException
import java.net.InetAddress
import java.net.SocketException

object ServerManager {
    fun manager(host: InetAddress, port: Int, collection: CollectionOfVehicles) {
        try {
            while (true) {
                val server = Server(host, port)
                try {
                    while (true) {
                        server.receive(collection)
                    }
                } catch (ex: ConnectException) {
                    println(ex.message)
                } catch (ex: SocketException) {
                    println("Соединение не установлено, запустите сервер, а затем клиент.")
                }
            }
        } catch (ex: BindException){
            println("Адрес уже используется")
        }
    }
}