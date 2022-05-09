package com.github.Diosa34.DMS_HelloWorld

import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.Socket

class Client(
    private val host: InetAddress,
    private val port: Int
) {
    private val sock: Socket = Socket(this.host ,this.port)
    private val arr: ByteArray = ByteArray(1024 * 1024)

    fun send(serializedCommand: ByteArray) {
        val os: OutputStream = sock.getOutputStream()
        println("Hello1")
        os.write(serializedCommand)
        println("Hello2")
    }

    fun receive() {
        val inputStream: InputStream = sock.getInputStream()
        println("Hello3")
        inputStream.read(arr)
        println("Hello4")
    }

    fun getArr(): ByteArray {
        return this.arr
    }
}