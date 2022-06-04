@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import kotlinx.serialization.Serializable

@Serializable
object Exit: BoundCommand, AbstractDescription {
    override val title: String = "exit"
    override val help: String = "завершить программу (без сохранения в файл)"
}