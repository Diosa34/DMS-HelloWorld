package com.github.Diosa34.DMS_HelloWorld.io

import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger

object ConsoleLogger: Logger {
    override fun print(message: String) {
        println(message)
    }
}