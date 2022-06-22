package com.github.Diosa34.DMS_HelloWorld.io

import com.github.Diosa34.DMS_HelloWorld.serialize.OneLineAnswer
import io.github.landgrafhomyak.itmo.dms_lab.io.AsByteArrayEncoder
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class SocketWrap (
    private val sock: SocketChannel,
) {
    private val serverToClientEncoder = AsByteArrayEncoder()

    @OptIn(ExperimentalUnsignedTypes::class)
    fun sendToSocket(answer: OneLineAnswer){
        OneLineAnswer.serializer().serialize(this.serverToClientEncoder, answer)
        this.sock.write(ByteBuffer.wrap(this.serverToClientEncoder.export().toByteArray()))
    }
}