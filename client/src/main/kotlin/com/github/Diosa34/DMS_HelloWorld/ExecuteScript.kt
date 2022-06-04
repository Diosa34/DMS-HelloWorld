@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.io.FileStringReader
import kotlinx.serialization.Serializable
import java.io.File
import java.io.FileNotFoundException

@Serializable
class ExecuteScript(
    private val path: String
): BoundCommand {
    fun execute(logger: Logger, client: Client){
        try {
            if (FileVerification.fullVerification(path)) {
                HistoryOfExecutingScripts.addScript(logger, path)
            }
        } catch (ex: FileNotFoundException) {
            println(ex.message)
        } catch (ex: FileVerificationException) {
            println(ex.message)
        }
        logger.print("Выполнение скрипта: ${File(path)}")
        RequestManager.manage(
            logger,
            1,
            FileStringReader(path),
            client)
        HistoryOfExecutingScripts.removeScript()
    }

    companion object Description: AbstractDescription {
        override val title: String = "execute_script"
        override val help: String = "считать и исполнить скрипт из указанного файла"
    }
}