package com.github.Diosa34.DMS_HelloWorld

import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.Socket

class Client(
    private val arr: ByteArray,
    private val host: InetAddress,
    private val port: Int= 6789

) {
    private val sock: Socket = Socket(this.host ,this.port)

    fun send(){
        val os: OutputStream = sock.getOutputStream()
        os.write(arr)
    }

    fun receive(){
        val inputStream: InputStream = sock.getInputStream()
        inputStream.read(arr)
    }

}