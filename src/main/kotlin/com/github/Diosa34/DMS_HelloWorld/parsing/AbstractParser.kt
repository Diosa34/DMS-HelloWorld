package com.github.Diosa34.DMS_HelloWorld.parsing

import com.github.Diosa34.DMS_HelloWorld.commands.Command

abstract class AbstractParser: Iterable<Pair<Command, Array<String>>?>{
    abstract fun hasNextLine(): Boolean

    abstract fun getNextLine(): String

    fun parse(): Pair<Command, Array<String>>? {
        val request = this.getNextLine().trim().split(Regex("""\s+"""))
        if (request.isEmpty()){
           return null
        }
        val command = Command.registry[request[0]] ?: return null
        return command to request.slice(1 until request.size).toTypedArray()
    }

    override operator fun iterator(): Iterator<Pair<Command, Array<String>>?> = iterator{
        while (this@AbstractParser.hasNextLine()) {
            yield(this@AbstractParser.parse())
        }
    }
}