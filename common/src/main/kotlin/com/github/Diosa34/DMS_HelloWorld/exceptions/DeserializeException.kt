package com.github.Diosa34.DMS_HelloWorld.exceptions

import java.awt.event.FocusEvent
import java.lang.Exception

class DeserializeException(
    override val message: String,
    override val cause: Exception? = null
) : Exception() {
}