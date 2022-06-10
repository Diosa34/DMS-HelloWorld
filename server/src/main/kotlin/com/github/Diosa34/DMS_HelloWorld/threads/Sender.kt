package com.github.Diosa34.DMS_HelloWorld.threads

import java.util.concurrent.BlockingQueue

class Sender(
    private var outputQueue: BlockingQueue<RequestInOutputQueue>
): Runnable {
    var isRunning = false

    override fun run() {
        try {
            while (this.isRunning) {
                val requestInOutputQueue = this.outputQueue.take()
                requestInOutputQueue.socketWrap.sendToSocket(requestInOutputQueue.answer)
            }
        } catch (e: InterruptedException) {
            println("Поток отправки ответов прерван")
        } finally {
            this.isRunning = false
        }
    }
}