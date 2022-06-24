package com.github.diosa.dms

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.BoundCommand
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.io.FileStringReader
import kotlinx.serialization.Serializable
import java.io.File
import java.io.FileNotFoundException

@Serializable
class ExecuteScript(
    private val path: String
): BoundCommand {
    fun execute(logger: Logger, client: com.github.diosa.dms.Client){
        try {
            if (com.github.diosa.dms.FileVerification.fullVerification(path)) {
                com.github.diosa.dms.HistoryOfExecutingScripts.addScript(logger, path)
            }
        } catch (ex: FileNotFoundException) {
            println(ex.message)
        } catch (ex: com.github.diosa.dms.FileVerificationException) {
            println(ex.message)
        }
        logger.print("Выполнение скрипта: ${File(path)}")
        RequestManager.manage(
            logger,
            1,
            FileStringReader(path),
            client)
        com.github.diosa.dms.HistoryOfExecutingScripts.removeScript()
    }

    companion object Description: AbstractDescription {
        override val title: String = "execute_script"
        override val help: String = "считать и исполнить скрипт из указанного файла"
    }
}