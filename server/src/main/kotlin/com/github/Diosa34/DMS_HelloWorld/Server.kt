@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.LogIn
import com.github.Diosa34.DMS_HelloWorld.commands.Register
import com.github.Diosa34.DMS_HelloWorld.exceptions.CollectionException
import com.github.Diosa34.DMS_HelloWorld.exceptions.DeserializeException
import com.github.Diosa34.DMS_HelloWorld.io.BufferLogger
import com.github.Diosa34.DMS_HelloWorld.serialize.CommandDeserializer
import com.github.Diosa34.DMS_HelloWorld.serialize.GeneralServerDecoder
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.serialization.encoding.Decoder
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
        val addres: SocketAddress = InetSocketAddress(host, port)
        serv.bind(addres)
        this.sock = serv.accept()
        this.log.info("Установлено новое подключение")
    }

    fun receive(collection: CollectionOfVehicles, usersCollection: SQLUsersCollection) {
        val commandArr = ByteArray(1024 * 1024)
        val userArr = ByteArray(1024 * 1024)
        val commandBuf = ByteBuffer.wrap(commandArr)
        val userBuf = ByteBuffer.wrap(userArr)
        this.sock.read(commandBuf)
        this.log.info("Получен новый запрос от клиента")
        (commandBuf as Buffer).flip()
        if (commandArr.contentEquals(ByteArray(1024 * 1024))) {
            this.serv.close()
            throw ConnectException()
        }
        val bufferLogger = BufferLogger(this.sock)
        val command: BoundCommand
        var user: User? = null
        try {
            command = CommandDeserializer.deserialize(commandArr.toUByteArray(), bufferLogger)
            if (command !is Register && command !is LogIn){
                this.sock.read(userBuf)
                (userBuf as Buffer).flip()
                user = User.serializer().deserialize(GeneralServerDecoder(userArr, 1, bufferLogger,
                    userArr.toUByteArray().iterator()))
            }
        } catch (ex: DeserializeException) {
            bufferLogger.print(ex.message)
            bufferLogger.bufSerialize()
            bufferLogger.flush()
            return
        }
        try {
            executeCall(command, bufferLogger, collection, usersCollection, user)
        } catch (ex: CollectionException) {
            bufferLogger.print(ex.message)
        } catch (ex: SQLException) {
            this.log.log(Level.WARNING, "SQLException: ", ex)
            bufferLogger.print("Ошибка обращения к базе данных")
        } catch (ex: IllegalStateException){
            this.log.log(Level.WARNING, "IllegalStateException: ", ex)
            bufferLogger.print("Доступ к базе данных не получен")
        }
        bufferLogger.bufSerialize()
        bufferLogger.flush()
        this.log.info("Ответ отправлен клиенту")
    }
}