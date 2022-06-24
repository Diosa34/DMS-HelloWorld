package com.github.diosa.dms.io

import com.github.diosa.dms.absctactions.Logger

object ConsoleLogger: Logger {
    override fun print(message: String) {
        println(message)
    }
}