package com.github.Diosa34.DMS_HelloWorld.absctactions

interface SystemCommand: BoundCommand {
    fun execute(logger: Logger)
}