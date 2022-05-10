package com.github.Diosa34.DMS_HelloWorld

object Help: BoundCommand, AbstractDescription {
    override val title: String = "help"
    override val help: String = "вывести справку по доступным командам"

    fun execute(logger: Logger, vararg descriptions: AbstractDescription) {
        for (i in descriptions.toMutableSet().apply { add(Help) }){
            logger.print("${i.title} - ${i.help}\n")
        }
    }

    override fun serialize(): ByteArray {
        return title.serialize()
    }
}
