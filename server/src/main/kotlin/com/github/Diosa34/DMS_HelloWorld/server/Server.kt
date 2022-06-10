package com.github.Diosa34.DMS_HelloWorld.server

import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.sql.SQLUsersCollection
import com.github.Diosa34.DMS_HelloWorld.threads.*
import java.net.*
import java.nio.channels.ServerSocketChannel
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.logging.Logger

class Server(
    host: InetAddress,
    port: Int,
    log: Logger,
) {
    private val log: Logger
    private val serv: ServerSocketChannel = ServerSocketChannel.open()

    init {
        this.log = log
        val address: SocketAddress = InetSocketAddress(host, port)
        serv.bind(address)
        this.log.info("Установлено новое подключение")
    }

    fun mainLoop(collection: CollectionOfVehicles, usersCollection: SQLUsersCollection) {
        val inputQueue: BlockingQueue<RequestInInputQueue> = ArrayBlockingQueue(1024)
        val outputQueue: BlockingQueue<RequestInOutputQueue> = ArrayBlockingQueue(1024)

        val receiver = ReceiveManager(this.serv, inputQueue)
        val handler = HandleManager(inputQueue, outputQueue, collection, usersCollection)
        val sender = SendManager(outputQueue)

        val receiveThread = Thread(receiver)
        receiveThread.start()

        val handleThread = Thread(handler)
        handleThread.start()

        val sendThread = Thread(sender)
        sendThread.start()

        receiveThread.join()
        handleThread.join()
        sendThread.join()
    }
}