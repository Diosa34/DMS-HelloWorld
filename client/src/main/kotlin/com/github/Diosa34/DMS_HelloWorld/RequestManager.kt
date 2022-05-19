package com.github.Diosa34.DMS_HelloWorld

import java.net.ConnectException
import java.net.SocketException
import kotlin.Throws

object RequestManager {
    @OptIn(ExperimentalUnsignedTypes::class)
    @JvmStatic
    @Throws(UnexpectedCommandException::class, ParseException::class)
    fun manage(logger: Logger, attempts: Int, creator: InstanceCreator?, stringReader: AbstractStringReader,
    client: Client){
        for (line in stringReader) {
            try {
                val command: BoundCommand = CommandParser.parse(logger, line, attempts, creator!!, stringReader)
                when (command) {
                    is ExecuteScript -> command.execute(logger, client)
                    is Exit -> {logger.print("Клиентское приложение завершило работу")
                        client.sock.close()
                        return
                    }
                }
                try {
                    client.send(command.serialize().toByteArray())
                    client.receive()
                } catch (ex: ConnectException) {
                    logger.print("Соединение прервано, перезапустите сервер, затем клиента")
                    return
                } catch (ex: SocketException) {
                    logger.print("Соединение прервано, перезапустите сервер, затем клиента")
                    return
                }
                logger.print(client.getArr().toUByteArray().iterator().deserializeString())
            } catch (e: UnexpectedCommandException) {
                logger.print(UnexpectedCommandException.message)
            } catch (e: ParseException) {
                logger.print(ParseException.message)
            }
        }
    }
}