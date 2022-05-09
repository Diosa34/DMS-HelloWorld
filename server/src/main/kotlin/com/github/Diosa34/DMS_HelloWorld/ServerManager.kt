package com.github.Diosa34.DMS_HelloWorld

import java.net.InetAddress

object ServerManager {
    fun manager(host: InetAddress, port: Int) {
        val server = Server(host, port)
        while (true) {
            server.receive()
        }
    }
}