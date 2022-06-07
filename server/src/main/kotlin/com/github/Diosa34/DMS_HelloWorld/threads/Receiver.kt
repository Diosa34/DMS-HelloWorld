package com.github.Diosa34.DMS_HelloWorld.threads

import com.github.Diosa34.DMS_HelloWorld.io.SocketWrap
import com.github.Diosa34.DMS_HelloWorld.serialize.Request
import io.github.landgrafhomyak.itmo.dms_lab.io.Client2ServerDecoder
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
            while (isRunning){
                val requestArr = ByteArray(1024 * 1024)
                this.read(requestArr)
                this.putCommand(requestArr)
            }
        } finally {
            this.isRunning = false
        }
    }

    private fun read(requestArr: ByteArray){
        val requestBuf = ByteBuffer.wrap(requestArr)
        this.sock.read(requestBuf)
        (requestBuf as Buffer).flip()
    }

    private fun putCommand(requestArr: ByteArray){
        val request = Request.serializer().deserialize(Client2ServerDecoder(requestArr.toUByteArray()))
        try {
            this.inputQueue.put(RequestInInputQueue(request.command, request.user, SocketWrap(this.sock)))
        } catch (e: InterruptedException) {
            e.printStackTrace()
            TODO()
        }
    }
}