package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.commands.SystemCommand

object Exit: SystemCommand {
    const val title: String = "exit"
    const val help: String = "завершить программу (без сохранения в файл)"

    override fun execute() {
        throw ExitException()
    }
}