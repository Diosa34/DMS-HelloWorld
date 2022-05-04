package com.github.Diosa34.DMS_HelloWorld.parsing

object ConsoleLogger: Logger {
    override fun print(message: String) {
        println(message)
    }
}