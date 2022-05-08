package com.github.Diosa34.DMS_HelloWorld

object Exit: BoundCommand, AbstractDescription {
    override val title: String = "exit"
    override val help: String = "завершить программу (без сохранения в файл)"

    fun serialize(): ByteArray {
        return title.serialize()
    }
}