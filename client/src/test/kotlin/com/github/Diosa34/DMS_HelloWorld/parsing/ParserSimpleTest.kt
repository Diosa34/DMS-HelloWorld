//package com.github.Diosa34.DMS_HelloWorld.parsing
//
//import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
//import kotlin.test.Test
//import kotlin.test.assertEquals
//
//internal class ParserSimpleTest {
//    private fun assertTypes(vararg commands: Pair<String, BoundCommand?>){
//        val parser = BufferedParser(*commands.map{(s, _) -> s}.toTypedArray())
//        for ((e, a) in commands.map{(_, c) -> c} zip parser.map{ca -> ca?.first}){
//            assertEquals(e, a)
//        }
//    }
//
//    @Test
//    fun itIsWorking() = this.assertTypes(
//        "help" to BoundCommand.registry["help"]!!, // TODO() переделать registry в enum
//        "" to null
//    )
//
//}