package com.github.Diosa34.DMS_HelloWorld

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class Server(
    var command: BoundCommand,
    var arr: ByteArray,
    val host: InetAddress,
    val port: Int,
    val addr: SocketAddress = InetSocketAddress(host, port),
    val sock: SocketChannel = SocketChannel.open(),
) {
    fun receive(){
        val buf: ByteBuffer = ByteBuffer.allocate(1024 * 1024)
        this.sock.read(buf);
    }

    fun send(){
        this.sock.connect(this.addr)
        val buf = ByteBuffer.wrap(arr)
        this.sock.write(buf)
    }
}