package com.github.Diosa34.DMS_HelloWorld

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel

class Server(
    val host: InetAddress,
    val port: Int,
    var sock: SocketChannel = SocketChannel.open()
) {
    val serv: ServerSocketChannel = ServerSocketChannel.open()
    init {
        val addr: SocketAddress = InetSocketAddress(host, port)
        serv.bind(addr)
        this.sock = serv.accept()
    }

    fun receive() {
        val arr = ByteArray(1024 * 1024)
        val buf = ByteBuffer.wrap(arr)
        println("Hello1")
        this.sock.read(buf)
        println("Hello2")
        buf.flip()
        val command: BoundCommand = CommandDeserializer.deserialize(arr)
        val bufferLogger = BufferLogger(this.sock)
        executeCall(command, bufferLogger)
        println("Hello3")
        bufferLogger.flush()
        println("Hello4")
    }
}