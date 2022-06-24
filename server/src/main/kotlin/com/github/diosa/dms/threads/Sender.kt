package com.github.diosa.dms.threads

import java.util.concurrent.BlockingQueue

class Sender(
    private var outputQueue: BlockingQueue<RequestInOutputQueue>
): Runnable {

    override fun run() {
        if (this.outputQueue.isNotEmpty()) {
            val requestInOutputQueue = this.outputQueue.take()
            requestInOutputQueue.socketWrap.sendToSocket(requestInOutputQueue.answer)
        }
    }
}