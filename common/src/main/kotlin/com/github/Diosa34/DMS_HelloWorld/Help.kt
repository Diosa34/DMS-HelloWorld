package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.commands.SystemCommand

object Help: SystemCommand {
    const val title: String = "help"
    const val help: String = "вывести справку по доступным командам"

    override fun execute() {
        TODO("Not yet implemented")
    }
}