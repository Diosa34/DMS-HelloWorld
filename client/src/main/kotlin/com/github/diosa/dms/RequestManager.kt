package com.github.diosa.dms

import com.github.diosa.dms.absctactions.AbstractStringReader
import com.github.diosa.dms.absctactions.BoundCommand
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.commands.Exit
import com.github.diosa.dms.exceptions.NotAuthorizedException
import com.github.diosa.dms.exceptions.ParseException
import com.github.diosa.dms.exceptions.UnexpectedCommandException
import com.github.diosa.dms.serialize.*
import com.github.diosa.dms.users.User
import io.github.landgrafhomyak.itmo.dms_lab.interop.TriesLimitExceedException
import io.github.landgrafhomyak.itmo.dms_lab.io.AsByteArrayDecoder
import io.github.landgrafhomyak.itmo.dms_lab.io.AsByteArrayEncoder
import java.net.ConnectException
import java.net.SocketException
import kotlin.Throws

object RequestManager {
    @OptIn(ExperimentalUnsignedTypes::class)
    @JvmStatic
    @Throws(UnexpectedCommandException::class, ParseException::class)
    fun manage(logger: Logger, attempts: Int, stringReader: AbstractStringReader, client: com.github.diosa.dms.Client){
        var user: User? = null
        for (line in stringReader) {
            try {
                if (line != "registry" && line != "log_in" && user == null) {
                throw NotAuthorizedException("Перед началом работы необходимо авторизоваться")
                }
                val command: BoundCommand = com.github.diosa.dms.CommandParser.parse(line, attempts)
                when (command) {
                    is ExecuteScript -> if (user != null) {command.execute(logger, client)}
                    is Exit -> {logger.print("Клиентское приложение завершило работу")
                        client.sock.close()
                        return
                    }
                }
                if (line != "execute_script"){
                    try {
                        var request = Request(command)
                        if (line != "registry" && line != "log_in" && user != null){
                            request = Request(command, user)
                        }
                        val encoder = AsByteArrayEncoder()
                        Request.serializer().serialize(encoder, request)
                        client.send(encoder.export().toByteArray())
                        client.receive()
                    } catch (ex: ConnectException) {
                        logger.print("Соединение прервано, перезапустите сервер, затем клиента")
                        return
                    } catch (ex: SocketException) {
                        logger.print("Соединение прервано, перезапустите сервер, затем клиента, ошибка сокета")
                        return
                    }
                    val answer = OneLineAnswer.serializer().deserialize(AsByteArrayDecoder(client.getArr().toUByteArray()))
                    logger.print(answer.result)
                    if (line == "log_in") {
                        user = answer.user
                    }
                }
            } catch (e: UnexpectedCommandException) {
                logger.print(UnexpectedCommandException.message)
            } catch (e: TriesLimitExceedException) {
                logger.print("Ввод команды завершён")
            } catch (e: ParseException) {
                logger.print(e.message)
            } catch (e: NotAuthorizedException) {
                logger.print(e.message)
            }
        }
    }
}