package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractStringReader
import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.collection.InstanceCreator
import com.github.Diosa34.DMS_HelloWorld.commands.Exit
import com.github.Diosa34.DMS_HelloWorld.exceptions.ParseException
import com.github.Diosa34.DMS_HelloWorld.exceptions.UnexpectedCommandException
import com.github.Diosa34.DMS_HelloWorld.serialize.deserializeString
import java.net.ConnectException
import java.net.SocketException
import java.util.logging.Level
import kotlin.Throws

object RequestManager {
    @OptIn(ExperimentalUnsignedTypes::class)
    @JvmStatic
    @Throws(UnexpectedCommandException::class, ParseException::class)
    fun manage(logger: Logger, attempts: Int, creator: InstanceCreator?, stringReader: AbstractStringReader,
               client: Client, log: java.util.logging.Logger){
        for (line in stringReader) {
            try {
                val command: BoundCommand = CommandParser.parse(logger, line, attempts, creator!!, stringReader, log)
                when (command) {
                    is ExecuteScript -> command.execute(logger, client)
                    is Exit -> {logger.print("Клиентское приложение завершило работу")
                        client.sock.close()
                        log.info("Сокет закрыт")
                        log.info("Завершена работа клиентского приложения")
                        return
                    }
                }
                if (line != "execute_script"){
                    try {
                        client.send(command.serialize().toByteArray())
                        client.receive()
                    } catch (ex: ConnectException) {
                        log.log(Level.SEVERE, "ConnectException: ", ex)
                        logger.print("Соединение прервано, перезапустите сервер, затем клиента")
                        return
                    } catch (ex: SocketException) {
                        log.log(Level.SEVERE, "SocketException: ", ex)
                        logger.print("Соединение прервано, перезапустите сервер, затем клиента, ошибка сокета")
                        return
                    }
                    logger.print(client.getArr().toUByteArray().iterator().deserializeString())
                }
            } catch (e: UnexpectedCommandException) {
                logger.print(UnexpectedCommandException.message)
            } catch (e: ParseException) {
                logger.print(ParseException.message)
            }
        }
    }
}