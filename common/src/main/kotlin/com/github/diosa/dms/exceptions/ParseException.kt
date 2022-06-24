package com.github.diosa.dms.exceptions

import java.lang.Exception

class ParseException(
    override val message: String = "Аргументы не были получены"
) : Exception()