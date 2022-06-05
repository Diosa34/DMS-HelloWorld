@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.io

import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.OneLineAnswer
import com.github.Diosa34.DMS_HelloWorld.serialize.serial
import com.github.Diosa34.DMS_HelloWorld.users.User
import io.github.landgrafhomyak.itmo.dms_lab.io.Server2ClientEncoder
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class BufferLogger(
    val sock: SocketChannel
) : Logger {
    private var user: User? = null
    private var buf = StringBuilder()
    private var arr = mutableListOf<UByteArray>()

    override fun print(message: String) {
        if (buf.isNotBlank()) {
            this.buf.append("\n$message")
        } else {
            this.buf.append(message)
        }
    }

    fun setUser(user: User){
        this.user = user
    }

    fun flush() {
        val answer = OneLineAnswer(this.user, this.buf.toString())
        OneLineAnswer.serializer().serialize(Server2ClientEncoder(this.arr), answer)
        this.sock.write(ByteBuffer.wrap(this.arr.flatten().toUByteArray().toByteArray()))
    }
}