package com.github.Diosa34.DMS_HelloWorld

interface SystemCommand: BoundCommand {
    fun execute(logger: Logger)
}