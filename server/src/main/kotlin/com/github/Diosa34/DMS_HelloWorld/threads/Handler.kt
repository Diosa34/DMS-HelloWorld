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

    override fun run() {
        if (this.inputQueue.isNotEmpty()) {
            val requestInQueue = inputQueue.take()
            println("Извлечён запрос из входящей очереди")
            val bufferLogger = BufferLogger(requestInQueue.socketWrap)
            try {
                executeCall(requestInQueue.command, bufferLogger, collection, usersCollection, requestInQueue.user)
            } catch (ex: CollectionException) {
                bufferLogger.print(ex.message)
//            } catch (ex: SQLException) {
//                bufferLogger.print("Ошибка обращения к базе данных")
            }
            bufferLogger.build()
            println(bufferLogger.answer.result)
            this.outputQueue.put(RequestInOutputQueue(bufferLogger.answer, bufferLogger.socketWrap))
            println("Запрос отправлен в исходящую очередь")
        }
    }
}