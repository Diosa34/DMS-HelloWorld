package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.BoundCommand
import com.github.diosa.dms.absctactions.Logger
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Help")
object Help: BoundCommand, AbstractDescription {
    override val title: String = "Help"
    override val help: String = "вывести справку по доступным командам"

    fun execute(logger: Logger, vararg descriptions: AbstractDescription) {
        for (i in descriptions.toMutableSet()){
            logger.print("${i.title} - ${i.help}")
        }
    }
}
