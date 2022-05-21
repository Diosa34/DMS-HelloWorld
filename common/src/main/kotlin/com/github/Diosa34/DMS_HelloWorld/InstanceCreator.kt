package com.github.Diosa34.DMS_HelloWorld

import java.time.ZonedDateTime

/**
 * Enums contain the execution of the desired constructor
 */
enum class InstanceCreator(private val creator : (AbstractStringReader) -> Vehicle?) : (AbstractStringReader) -> Vehicle? {
    CREATE_FROM_FILE({stringReader -> instanceCreate(stringReader.getNextLine(), stringReader.getNextLine(), stringReader.getNextLine(), stringReader.getNextLine(),
        stringReader.getNextLine(), stringReader.getNextLine(), 1)}),
    CREATE_WITH_INPUT({ stringReader -> instanceCreate(ConsoleLogger, 3)});

    override operator fun invoke(stringReader: AbstractStringReader) = creator(stringReader)
}