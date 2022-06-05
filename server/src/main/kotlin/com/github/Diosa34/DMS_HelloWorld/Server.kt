@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.exceptions.CollectionException
import com.github.Diosa34.DMS_HelloWorld.exceptions.DeserializeException
import com.github.Diosa34.DMS_HelloWorld.io.BufferLogger
import com.github.Diosa34.DMS_HelloWorld.serialize.Request
import io.github.landgrafhomyak.itmo.dms_lab.io.Client2ServerDecoder
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
    private var sock: SocketChannel = SocketChannel.open()
) {
    private val log: Logger
    private val serv: ServerSocketChannel = ServerSocketChannel.open()

    init {
        this.log = log
        val address: SocketAddress = InetSocketAddress(host, port)
        serv.bind(address)
        this.sock = serv.accept()
        this.log.info("Установлено новое подключение")
    }

    fun receive(collection: CollectionOfVehicles, usersCollection: SQLUsersCollection) {
        val requestArr = ByteArray(1024 * 1024)
        val requestBuf = ByteBuffer.wrap(requestArr)
        this.sock.read(requestBuf)
        this.log.info("Получен новый запрос от клиента")
        (requestBuf as Buffer).flip()
        if (requestArr.contentEquals(ByteArray(1024 * 1024))) {
            this.serv.close()
            throw ConnectException()
        }
        val bufferLogger = BufferLogger(this.sock)
        val request: Request
        try {
            request = Request.serializer().deserialize(Client2ServerDecoder(requestArr.toUByteArray()))
        } catch (ex: DeserializeException) {
            bufferLogger.print(ex.message)
            bufferLogger.flush()
            return
        }
        try {
            executeCall(request.command, bufferLogger, collection, usersCollection, request.user)
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