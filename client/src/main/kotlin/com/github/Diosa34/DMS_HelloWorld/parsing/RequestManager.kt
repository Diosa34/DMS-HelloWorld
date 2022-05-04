package com.github.Diosa34.DMS_HelloWorld.parsing

import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractParser.parse
import com.github.Diosa34.DMS_HelloWorld.Application
import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.commands.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.commands.SystemCommand
import kotlin.Throws
import com.github.Diosa34.DMS_HelloWorld.enums.InstanceCreator

class RequestManager(fileToSave: String?) {
    var applicationInstance: Application

    @Throws(UnexpectedCommandException::class, ParseException::class)
    fun read(attempts: Int, creator: InstanceCreator?, stringReader: AbstractStringReader) {
        while (stringReader.hasNextLine()) {
            try {
                val command: BoundCommand = parse(stringReader.getNextLine(), attempts, creator!!, stringReader)
                if (command is ApplicableToCollection) {
                    command.execute(CollectionOfVehicles.globalCollection)
                } else if (command is SystemCommand){
                    command.execute()
                }
            } catch (e: UnexpectedCommandException) {
                ConsoleLogger.print(UnexpectedCommandException.message)
            } catch (e: ParseException) {
                ConsoleLogger.print(ParseException.message)
            }
        }
    }

    init {
        applicationInstance = Application(fileToSave)
    }
}