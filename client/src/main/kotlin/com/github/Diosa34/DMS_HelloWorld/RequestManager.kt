package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractStringReader
import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.commands.Exit
import com.github.Diosa34.DMS_HelloWorld.exceptions.ParseException
import com.github.Diosa34.DMS_HelloWorld.exceptions.UnexpectedCommandException
import com.github.Diosa34.DMS_HelloWorld.serialize.GeneralEncoder
import com.github.Diosa34.DMS_HelloWorld.serialize.GeneralServerDecoder
import com.github.Diosa34.DMS_HelloWorld.serialize.InterfaceSerializer
import com.github.Diosa34.DMS_HelloWorld.serialize.deserializeString
import com.github.Diosa34.DMS_HelloWorld.users.User
import java.net.ConnectException
import java.net.SocketException
import kotlin.Throws

object RequestManager {
    @OptIn(ExperimentalUnsignedTypes::class)
    @JvmStatic
    @Throws(UnexpectedCommandException::class, ParseException::class)
    fun manage(logger: Logger, attempts: Int, stringReader: AbstractStringReader, client: Client){
        var user: User? = null
        for (line in stringReader) {
            try {
                val command: BoundCommand = CommandParser.parse(logger, line, attempts, stringReader)
                when (command) {
                    is ExecuteScript -> if (user != null) {command.execute(logger, client)}
                    is Exit -> {logger.print("Клиентское приложение завершило работу")
                        client.sock.close()
                        return
                    }
                }
                if (line != "execute_script"){
                    val commandArr = ByteArray(1024*1024)
                    val userArr = ByteArray(1024*1024)
                    try {
                        if (user != null || line == "register" || line == "log_in"){
                            InterfaceSerializer.serialize(GeneralEncoder(commandArr.toUByteArray()), command)
                            client.send(commandArr)
                        } else {
                            throw NotAuthorized("Перед началом работы необходимо авторизоваться")
                        }
                        if (line != "register" && line != "log_in" && user != null){
                            User.serializer().serialize(GeneralEncoder(userArr.toUByteArray()), user)
                            client.send(userArr)
                        }
                        client.receive()
                    } catch (ex: ConnectException) {
                        logger.print("Соединение прервано, перезапустите сервер, затем клиента")
                        return
                    } catch (ex: SocketException) {
                        logger.print("Соединение прервано, перезапустите сервер, затем клиента, ошибка сокета")
                        return
                    }
                    val ansIterator = client.getArr().toUByteArray().iterator()
                    if (line == "log_in") {
                        user = User.serializer().deserialize(GeneralServerDecoder(client.getArr(), 1, logger, ansIterator))
                    }
                    logger.print(ansIterator.deserializeString())
                }
            } catch (e: UnexpectedCommandException) {
                logger.print(UnexpectedCommandException.message)
            } catch (e: ParseException) {
                logger.print(e.message)
            } catch (e: NotAuthorized) {
                logger.print(e.message)
            }
        }
    }
}