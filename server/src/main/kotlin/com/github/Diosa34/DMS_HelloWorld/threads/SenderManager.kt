package com.github.Diosa34.DMS_HelloWorld.threads

import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SenderManager(
    private var outputQueue: BlockingQueue<RequestInOutputQueue>
): Runnable {
    private val service: ExecutorService = Executors.newFixedThreadPool(5)
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