package com.github.diosa.dms.threads

import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.exceptions.CollectionException
import com.github.diosa.dms.executeCall
import com.github.diosa.dms.io.BufferLogger
import com.github.diosa.dms.sql.SQLAndMemoryCollection
import com.github.diosa.dms.sql.SQLUsersCollection
import java.sql.SQLException
import java.util.concurrent.BlockingQueue

class Handler(
    private var inputQueue: BlockingQueue<RequestInInputQueue>,
    private var outputQueue: BlockingQueue<RequestInOutputQueue>,
    private val collection: SQLAndMemoryCollection,
    private val usersCollection: SQLUsersCollection
): Runnable {

    override fun run() {
        if (this.inputQueue.isNotEmpty()) {
            val requestInQueue = inputQueue.take()
            println("Извлечён запрос из входящей очереди")
            val bufferLogger = BufferLogger(requestInQueue.socketWrap)
            try {
                executeCall(requestInQueue.command, bufferLogger, collection, usersCollection, requestInQueue.user)
            } catch (ex: CollectionException) {
                bufferLogger.print(ex.message)
            } catch (ex: SQLException) {
                bufferLogger.print("Ошибка обращения к базе данных")
            }
            bufferLogger.build()
            println(bufferLogger.answer.result)
            try {
                this.outputQueue.put(RequestInOutputQueue(bufferLogger.answer, bufferLogger.socketWrap))
            } catch (e: InterruptedException){
                Thread.currentThread().interrupt()
            }
            println("Запрос отправлен в исходящую очередь")
        }
    }
}