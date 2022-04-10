package com.github.Diosa34.DMS_HelloWorld.enums

import com.github.Diosa34.DMS_HelloWorld.classes.Vehicle
import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractParser
import java.time.ZonedDateTime
import java.util.*

/**
 * Enums contain the execution of the desired constructor
 */
enum class InstanceCreator(private val creator : (AbstractParser) -> Vehicle?) : (AbstractParser) -> Vehicle? {
    CREATE_FROM_FILE({scanner -> com.github.Diosa34.DMS_HelloWorld.commands.instanceCreate(Vehicle.idGenerator().toString(), ZonedDateTime.now().toString(),
        scanner.getNextLine(), scanner.getNextLine(), scanner.getNextLine(), scanner.getNextLine(), scanner.getNextLine(), scanner.getNextLine(), 1)}),
    CREATE_WITH_INPUT({ com.github.Diosa34.DMS_HelloWorld.commands.instanceCreate(3)});

    override operator fun invoke(scanner: AbstractParser) = creator(scanner)
}