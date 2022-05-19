package com.github.Diosa34.DMS_HelloWorld

import java.net.InetAddress

fun main() {
    val host = InetAddress.getLocalHost()
    val port = 6789
//    SQLManager.main()
    ServerManager.manager(host, port)
}
