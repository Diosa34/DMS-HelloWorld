package com.github.Diosa34.DMS_HelloWorld.threads

import java.util.concurrent.BlockingQueue

class Sender(
    private var outputQueue: BlockingQueue<RequestInOutputQueue>
): Runnable {

    override fun run() {
        if (this.outputQueue.isNotEmpty()) {
            val requestInOutputQueue = this.outputQueue.take()
            println("Sender извлёк ответ из очереди")
            requestInOutputQueue.socketWrap.sendToSocket(requestInOutputQueue.answer)
        }
    }
}