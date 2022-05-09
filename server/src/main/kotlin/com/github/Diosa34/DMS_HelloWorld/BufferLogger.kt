package com.github.Diosa34.DMS_HelloWorld

import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class BufferLogger(
    val sock: SocketChannel
) : Logger {
    var buf = ""

    override fun print(message: String) {
        this.buf += message
    }

    fun flush(){
        this.sock.write(ByteBuffer.wrap(this.buf.serialize()))
    }
}