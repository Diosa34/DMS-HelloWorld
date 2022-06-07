package com.github.Diosa34.DMS_HelloWorld.threads

import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.sql.SQLUsersCollection
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HandlerManager(
    private var inputQueue: BlockingQueue<RequestInInputQueue>,
    private var outputQueue: BlockingQueue<RequestInOutputQueue>,
    private val collection: CollectionOfVehicles,
    private val usersCollection: SQLUsersCollection
): Runnable {
    private val service: ExecutorService = Executors.newFixedThreadPool(3)
    private var isRunning = false

    override fun run() {
        this.isRunning = true
        try {
            while (this.isRunning){
                service.execute(Handler(this.inputQueue, this.outputQueue, this.collection, this.usersCollection))
            }
        } finally {
            this.isRunning = false
        }
    }
}