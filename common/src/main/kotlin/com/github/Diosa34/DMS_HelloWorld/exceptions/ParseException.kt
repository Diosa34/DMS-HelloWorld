package com.github.Diosa34.DMS_HelloWorld.exceptions

import java.lang.Exception

class ParseException(
    override val message: String = "Аргументы не были получены"
) : Exception()