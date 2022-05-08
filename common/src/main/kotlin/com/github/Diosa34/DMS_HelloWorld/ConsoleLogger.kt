package com.github.Diosa34.DMS_HelloWorld

object ConsoleLogger: Logger {
    override fun print(message: String) {
        println(message)
    }
}