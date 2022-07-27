package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.BoundCommand
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Exit")
object Exit: BoundCommand, AbstractDescription {
    override val title: String = "Exit"
    override val help: String = "завершить программу (без сохранения в файл)"
}