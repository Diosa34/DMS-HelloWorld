package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractStringReader
import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.commands.Exit
import com.github.Diosa34.DMS_HelloWorld.exceptions.ParseException
import com.github.Diosa34.DMS_HelloWorld.exceptions.UnexpectedCommandException
import com.github.Diosa34.DMS_HelloWorld.serialize.*
import com.github.Diosa34.DMS_HelloWorld.users.User
import io.github.landgrafhomyak.itmo.dms_lab.io.Client2ServerEncoder
import io.github.landgrafhomyak.itmo.dms_lab.io.Server2ClientDecoder
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
                    val requestArr = mutableListOf<UByteArray>()
                    try {
                        var request = Request(command)
                        if (line != "registry" && line != "log_in" && user != null){
                            request = Request(command, user)
                        } else if (line != "registry" && line != "log_in" && user == null) {
                            throw NotAuthorized("Перед началом работы необходимо авторизоваться")
                        }
                        Request.serializer().serialize(Client2ServerEncoder(requestArr), request)
                        client.send(requestArr.flatten().toUByteArray().toByteArray())
                        client.receive()
                    } catch (ex: ConnectException) {
                        logger.print("Соединение прервано, перезапустите сервер, затем клиента")
                        return
                    } catch (ex: SocketException) {
                        logger.print("Соединение прервано, перезапустите сервер, затем клиента, ошибка сокета")
                        return
                    }
                    if (line != "help" || line != "info" || line != "show") {
                        val answer = OneLineAnswer.serializer().deserialize(Server2ClientDecoder(client.getArr().toUByteArray()))
                        user = answer.user
                        logger.print(answer.result)
                    }
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