package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("help")
object Help: BoundCommand, AbstractDescription {
    override val title: String = "help"
    override val help: String = "вывести справку по доступным командам"

    fun execute(logger: Logger, vararg descriptions: AbstractDescription) {
        for (i in descriptions.toMutableSet().apply { add(Help) }){
            logger.print("${i.title} - ${i.help}")
        }
    }
}
