package com.github.Diosa34.DMS_HelloWorld.parsing

import com.github.Diosa34.DMS_HelloWorld.commands.Command
import kotlin.test.Test
import kotlin.test.assertEquals

class ParserSimpleTest {
    private fun assertTypes(vararg commands: Pair<String, Command?>){
        val parser = BufferedParser(*commands.map{(s, _) -> s}.toTypedArray())
        for ((e, a) in commands.map{(_, c) -> c} zip parser.map{ca -> ca?.first}){
            assertEquals(e, a)
        }
    }

    @Test
    fun itIsWorking() = this.assertTypes(
        "help" to Command.registry["help"]!! // TODO() переделать registry в enum
    )

}