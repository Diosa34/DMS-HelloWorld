package com.github.diosa.dms.exceptions

import java.lang.Exception

class DeserializeException(
    override val message: String,
    override val cause: Exception? = null
) : Exception()