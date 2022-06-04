@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.io

import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.serial
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class BufferLogger(
    val sock: SocketChannel
) : Logger {
    private var buf = StringBuilder()
    private var arr: ByteArray = ByteArray(1024*1024)

    override fun print(message: String) {
        if (buf.isNotBlank()) {
            this.buf.append("\n$message")
        } else {
            this.buf.append(message)
        }
    }

    fun bufSerialize(){
        val str: UByteArray = this.buf.toString().toByteArray(Charsets.UTF_8).toUByteArray()
        this.arr += (str.size.serial() + str).toByteArray()
    }

    fun userToArr(user: ByteArray){
        this.arr += user
    }

    fun flush() {
        this.sock.write(ByteBuffer.wrap(this.arr))
    }
}