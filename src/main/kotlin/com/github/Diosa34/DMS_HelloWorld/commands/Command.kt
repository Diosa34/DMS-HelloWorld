package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.Application
import com.github.Diosa34.DMS_HelloWorld.enums.InstanceCreator
import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractParser
import java.util.*

/**
 * Entity describing the command
 */
class Command(
        val name: String,
        val help: String,
        private val executeFun: (Array<String>, Int, InstanceCreator, AbstractParser, Application) -> Unit, // тип функции, которая принимает массив строк и возвращает Unit(void в джаве)
) {
    fun execute(requestString: Array<String>, attempts: Int, creator: InstanceCreator, parser: AbstractParser, globalArgs: Application) =
        executeFun(requestString, attempts, creator, parser, globalArgs)

// в котлине нет слова static, поэтому все статические члены выносятся в companion object
    companion object {
        @JvmField // доступ из джавы без геттера
//      @JvmStatic делает переменную статической не только в котлине, но и в джаве
        val registry = information
    }
}
