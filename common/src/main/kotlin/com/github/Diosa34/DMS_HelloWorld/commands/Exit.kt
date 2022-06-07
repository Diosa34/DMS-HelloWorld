package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("exit")
object Exit: BoundCommand, AbstractDescription {
    override val title: String = "exit"
    override val help: String = "завершить программу (без сохранения в файл)"
}