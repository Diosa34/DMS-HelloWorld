package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.AbstractParser.parse
import kotlin.Throws

class RequestManager(fileToSave: String?) {
    var applicationInstance: Application

    companion object{
        @JvmStatic
        @Throws(UnexpectedCommandException::class, ParseException::class)
        fun read(logger: Logger, attempts: Int, creator: InstanceCreator?, stringReader: AbstractStringReader) {
            while (stringReader.hasNextLine()) {
                try {
                    val command: BoundCommand = parse(logger, stringReader.getNextLine(), attempts, creator!!, stringReader)
                    when (command) {
                        is ApplicableToCollection -> command.execute(logger, CollectionOfVehicles.globalCollection)
                        is SystemCommand -> command.execute(logger)
                        is Help -> command.execute(logger, Add.Description, AddIfMin.Description, Clear,
                            CountByType.Description, ExecuteScript.Description, Exit, GroupCountingByType, Help,
                        Info, RemoveById.Description, RemoveFirst, RemoveLower.Description, Show, SumOfEnginePower,
                        Update.Description)
                        is Exit -> return
                    }
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