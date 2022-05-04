package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.HistoryOfExecutingScripts
import com.github.Diosa34.DMS_HelloWorld.commands.SystemCommand
import java.io.File

class ExecuteScript(
    private val path: String,
    private val globalArgs: Application
): SystemCommand {
    fun execute(collection: HistoryOfExecutingScripts){
        println("Выполнение скрипта: ${File(path)}")
//        val executor = CommandExecutor(
//                globalArgs.filepath,
//                FileStringReader(FileInputStream(File(path)))
//        )
//        executor.execute(1, InstanceCreator.CREATE_FROM_FILE)
    }

    companion object{
        const val title: String = "execute_script"
        const val help: String = "считать и исполнить скрипт из указанного файла"
    }
}