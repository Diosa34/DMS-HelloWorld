package com.github.Diosa34.DMS_HelloWorld.threads

import java.nio.channels.ServerSocketChannel
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ReceiveManager(
    private val serv: ServerSocketChannel,
    private var queue: BlockingQueue<RequestInInputQueue>,
): Runnable {
    private val service: ExecutorService = Executors.newFixedThreadPool(3)
    var isRunning = false
    private set

    override fun run() {
        this.isRunning = true
        try {
            while (this.isRunning){
                val sock = this.serv.accept()
                service.execute(Receiver(sock, this.queue))
            }
        } finally {
            this.isRunning = false
        }
    }
}