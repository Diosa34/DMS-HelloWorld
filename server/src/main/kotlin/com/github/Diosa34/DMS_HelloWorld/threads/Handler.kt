package com.github.Diosa34.DMS_HelloWorld.threads

import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.exceptions.CollectionException
import com.github.Diosa34.DMS_HelloWorld.executeCall
import com.github.Diosa34.DMS_HelloWorld.io.BufferLogger
import com.github.Diosa34.DMS_HelloWorld.sql.SQLUsersCollection
import java.sql.SQLException
import java.util.concurrent.BlockingQueue

class Handler(
    private var inputQueue: BlockingQueue<RequestInInputQueue>,
    private var outputQueue: BlockingQueue<RequestInOutputQueue>,
    private val collection: CollectionOfVehicles,
    private val usersCollection: SQLUsersCollection
): Runnable {
    var isRunning = false

    override fun run() {
        this.isRunning = true
        try {
            while (this.isRunning) {
                val requestInQueue = inputQueue.take()
                val bufferLogger = BufferLogger(requestInQueue.socketWrap)
                try {
                    executeCall(requestInQueue.command, bufferLogger, collection, usersCollection, requestInQueue.user)
                } catch (ex: CollectionException) {
                    bufferLogger.print(ex.message)
                } catch (ex: SQLException) {
                    bufferLogger.print("Ошибка обращения к базе данных")
                } catch (ex: IllegalStateException) {
                    bufferLogger.print("Доступ к базе данных не получен")
                }
                bufferLogger.build()
                this.outputQueue.put(RequestInOutputQueue(bufferLogger.answer, bufferLogger.socketWrap))
            }
        } catch (e: InterruptedException) {
            println("Поток обработки запросов завершился")
            return
        } finally {
            this.isRunning = false
        }
    }
}