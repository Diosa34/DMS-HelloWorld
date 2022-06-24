package com.github.diosa.dms.threads

import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.sql.SQLUsersCollection
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

class HandleManager(
    private var inputQueue: BlockingQueue<RequestInInputQueue>,
    private var outputQueue: BlockingQueue<RequestInOutputQueue>,
    private val collection: CollectionOfVehicles,
    private val usersCollection: SQLUsersCollection
) : Runnable {
    private val service: ExecutorService = Executors.newCachedThreadPool()
    private var isRunning = false

    override fun run() {
        this.isRunning = true
        try {
            while (this.isRunning) {
                service.execute(Handler(this.inputQueue, this.outputQueue, this.collection, this.usersCollection))
            }
        } finally {
            this.isRunning = false
        }
    }
}