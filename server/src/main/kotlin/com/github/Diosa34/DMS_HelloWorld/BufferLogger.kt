package com.github.Diosa34.DMS_HelloWorld

import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class BufferLogger(
    val sock: SocketChannel
) : Logger {
    var buf = ""

    override fun print(message: String) {
        if (buf != "") {
            this.buf += "\n$message"
        } else {
            this.buf += message
        }
    }

    fun flush() {
        this.sock.write(ByteBuffer.wrap(this.buf.serialize()))
    }
}