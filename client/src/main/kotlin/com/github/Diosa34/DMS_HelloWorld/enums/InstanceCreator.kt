package com.github.Diosa34.DMS_HelloWorld.enums

import com.github.Diosa34.DMS_HelloWorld.classes.Vehicle
import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractParser
import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractStringReader
import java.time.ZonedDateTime

/**
 * Enums contain the execution of the desired constructor
 */
enum class InstanceCreator(private val creator : (AbstractStringReader) -> Vehicle?) : (AbstractStringReader) -> Vehicle? {
    CREATE_FROM_FILE({stringReader -> com.github.Diosa34.DMS_HelloWorld.commands.instanceCreate(
        Vehicle.idGenerator().toString(), ZonedDateTime.now().toString(),
        stringReader.getNextLine(), stringReader.getNextLine(), stringReader.getNextLine(), stringReader.getNextLine(),
        stringReader.getNextLine(), stringReader.getNextLine(), 1)}),
    CREATE_WITH_INPUT({ com.github.Diosa34.DMS_HelloWorld.commands.instanceCreate(3)});

    override operator fun invoke(stringReader: AbstractStringReader) = creator(stringReader)
}