package com.github.diosa.dms.client

import com.github.diosa.dms.FileVerification
import com.github.diosa.dms.FileVerificationException
import HistoryOfExecutingScripts
import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.BoundCommand
import com.github.diosa.dms.io.FileStringReader
import com.github.diosa.dms.mainGUI.Alert
import kotlinx.serialization.Serializable
import java.io.File
import java.io.FileNotFoundException

@Serializable
class ExecuteScript(
    private val path: String
): BoundCommand {
    fun execute(){
        try {
            if (FileVerification.fullVerification(path)) {
                HistoryOfExecutingScripts.addScript(path)
            }
        } catch (ex: FileNotFoundException) {
            println(ex.message)
        } catch (ex: FileVerificationException) {
            println(ex.message)
        }
        Alert.notification("Выполнение скрипта: ${File(path)}")
//        RequestManager.manage(
//            1,
//            FileStringReader(path)
//        )
        HistoryOfExecutingScripts.removeScript()
    }

    companion object Description: AbstractDescription {
        override val title: String = "execute_script"
        override val help: String = "считать и исполнить скрипт из указанного файла"
    }
}