@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.exceptions.CollectionException
import com.github.Diosa34.DMS_HelloWorld.exceptions.DeserializeException
import com.github.Diosa34.DMS_HelloWorld.serialize.CommandDeserializer
import java.net.*
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.sql.SQLException
import java.util.logging.Level
import java.util.logging.Logger

class Server(
    host: InetAddress,
    port: Int,
    log: Logger,
    var sock: SocketChannel = SocketChannel.open()
) {
    private val log: Logger
    val serv: ServerSocketChannel = ServerSocketChannel.open()


    init {
        this.log = log
        val addr: SocketAddress = InetSocketAddress(host, port)
        serv.bind(addr)
        this.sock = serv.accept()
        this.log.info("Установлено новое подключение")
    }

    fun receive(collection: CollectionOfVehicles, usersCollection: SQLUsersCollection) {
        val arr = ByteArray(1024 * 1024)
        val buf = ByteBuffer.wrap(arr)
        this.sock.read(buf)
        this.log.info("Получен новый запрос от клиента")
        (buf as Buffer).flip()
        if (arr.contentEquals(ByteArray(1024 * 1024))) {
            this.serv.close()
            throw ConnectException()
        }
        val bufferLogger = BufferLogger(this.sock)
        val command: BoundCommand
        try {
            command = CommandDeserializer.deserialize(arr.toUByteArray())
        } catch (ex: DeserializeException) {
            bufferLogger.print(ex.message)
            bufferLogger.flush()
            return
        }
        try {
            executeCall(command, bufferLogger, collection, usersCollection)
        } catch (ex: CollectionException) {
            bufferLogger.print(ex.message)
        } catch (ex: SQLException) {
            this.log.log(Level.WARNING, "SQLException: ", ex)
            bufferLogger.print("Ошибка обращения к базе данных")
        } catch (ex: IllegalStateException){
            this.log.log(Level.WARNING, "IllegalStateException: ", ex)
            bufferLogger.print("Доступ к базе данных не получен")
        }

        bufferLogger.flush()
        this.log.info("Ответ отправлен клиенту")
    }
}