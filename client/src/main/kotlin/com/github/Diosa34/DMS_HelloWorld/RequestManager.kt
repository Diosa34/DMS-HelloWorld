package com.github.Diosa34.DMS_HelloWorld

import kotlin.Throws

class RequestManager(fileToSave: String?) {
    var applicationInstance: Application


    companion object{
        @JvmStatic
        @Throws(UnexpectedCommandException::class, ParseException::class)
        fun manage(logger: Logger, attempts: Int, creator: InstanceCreator?, stringReader: AbstractStringReader,
        client: Client){
            for (line in stringReader) {
                try {
                    val command: BoundCommand = CommandParser.parse(logger, line, attempts, creator!!, stringReader)
                    when (command) {
                        is ExecuteScript -> command.execute(logger, client)
                        is Exit -> return
                    }
                    client.send(command.serialize())
                    client.receive()
                    logger.print(client.getArr().iterator().deserializeString())
                } catch (e: UnexpectedCommandException) {
                    logger.print(UnexpectedCommandException.message)
                } catch (e: ParseException) {
                    logger.print(ParseException.message)
                }
            }
        }
    }

    init {
        applicationInstance = Application(fileToSave)
    }
}