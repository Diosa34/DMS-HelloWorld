package com.github.diosa.dms.threads

import com.github.diosa.dms.io.SocketWrap
import com.github.diosa.dms.serialize.Request
import io.github.landgrafhomyak.itmo.dms_lab.io.Client2ServerDecoder
import java.net.ConnectException
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel
import java.util.concurrent.BlockingQueue

class Receiver(
    private val sock: SocketChannel,
    private val inputQueue: BlockingQueue<RequestInInputQueue>
): Runnable {
    var isRunning = false

    override fun run() {
        this.isRunning = true
        try {
            while (this.isRunning){
                val requestArr = ByteArray(1024 * 1024)
                this.read(requestArr)
                this.putCommand(requestArr)
            }
        } finally {
            this.isRunning = false
        }
    }

    private fun read(requestArr: ByteArray){
//        if (requestArr.contentEquals(ByteArray(1024 * 1024))) {
//            throw ConnectException()
//        }

        val requestBuf = ByteBuffer.wrap(requestArr)
        this.sock.read(requestBuf)
        (requestBuf as Buffer).flip()
    }

    private fun putCommand(requestArr: ByteArray){
        val request = Request.serializer().deserialize(Client2ServerDecoder(requestArr.toUByteArray()))
        try {
            this.inputQueue.put(RequestInInputQueue(request.command, request.user, SocketWrap(this.sock)))
        } catch (e: InterruptedException) {
            println("Поток чтения запросов прерван")
        }
    }
}