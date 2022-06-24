package com.github.diosa.dms.threads

import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SendManager(
    private var outputQueue: BlockingQueue<RequestInOutputQueue>
): Runnable {
    private val service: ExecutorService = Executors.newCachedThreadPool()
    private var isRunning = false

    override fun run() {
        this.isRunning = true
        try {
            while (this.isRunning){
                service.execute(Sender(this.outputQueue))
            }
        } finally {
            this.isRunning = false
        }
    }
}