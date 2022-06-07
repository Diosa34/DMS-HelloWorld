package com.github.Diosa34.DMS_HelloWorld.io

import com.github.Diosa34.DMS_HelloWorld.serialize.OneLineAnswer
import io.github.landgrafhomyak.itmo.dms_lab.io.Server2ClientEncoder
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class SocketWrap @OptIn(ExperimentalUnsignedTypes::class) constructor(
    private val sock: SocketChannel,
    private var arr: MutableList<UByteArray> = mutableListOf<UByteArray>()
) {
    @OptIn(ExperimentalUnsignedTypes::class)
    fun sendToSocket(answer: OneLineAnswer){
        OneLineAnswer.serializer().serialize(Server2ClientEncoder(this.arr), answer)
        this.sock.write(ByteBuffer.wrap(arr.flatten().toUByteArray().toByteArray()))
    }
}