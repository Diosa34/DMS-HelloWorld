package com.github.diosa.dms.absctactions

interface SystemCommand: BoundCommand {
    fun execute(logger: Logger)
}