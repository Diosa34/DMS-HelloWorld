@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

import java.io.File

class ExecuteScript(
    private val path: String
): BoundCommand {
    fun execute(logger: Logger, client: Client){
        logger.print("Выполнение скрипта: ${File(path)}")
        RequestManager.manage(logger, 1, InstanceCreator.CREATE_FROM_FILE, FileStringReader(path), client)
        HistoryOfExecutingScripts.removeScript()
    }

    override fun serialize(): UByteArray{
        var bytes: UByteArray = title.serialize()
        bytes += this.path.serialize()
        return bytes
    }

    companion object Description: AbstractDescription {
        override val title: String = "execute_script"
        override val help: String = "считать и исполнить скрипт из указанного файла"
    }
}