package com.github.diosa.dms.client

import com.github.diosa.dms.absctactions.BoundCommand
import com.github.diosa.dms.commands.*
import com.github.diosa.dms.exceptions.ParseException
import com.github.diosa.dms.exceptions.UnexpectedCommandException
import io.github.landgrafhomyak.itmo.dms_lab.interop.ConsoleInputDecoder
import kotlinx.serialization.encoding.Decoder
import kotlin.jvm.Throws

/**
 * Abstract class for any parser
 */
object CommandParser{
     /**
     * return Pair<Command, Array<String>>? where Command is name and Array<String>>? is nullable attributes
     */
    @JvmStatic
    @Throws(UnexpectedCommandException::class, ParseException::class)
    fun parse(str: String, attempts: Int): BoundCommand {
        val request = str.trim().split(Regex("""\s+"""))
        if (request.isEmpty()){
            throw UnexpectedCommandException()
        }

        val decoder: Decoder = ConsoleInputDecoder(attempts.toUInt())
//        if (stringReader is FileStringReader) {
//            decoder = CompositeFileDecoderDelegate(logger, attempts, stringReader)
//        }

        val command: BoundCommand = when (request[0]) {
            "registry" -> SignUp.serializer().deserialize(decoder)
            "log_in" -> LogIn.serializer().deserialize(decoder)
            "add" -> Add.serializer().deserialize(decoder)
            "add_if_min" -> AddIfMin.serializer().deserialize(decoder)
            "clear" -> Clear.serializer().deserialize(decoder)
            "count_by_type" -> CountByType.serializer().deserialize(decoder)
            "execute_script" -> ExecuteScript.serializer().deserialize(decoder)
            "exit" -> Exit.serializer().deserialize(decoder)
            "group_counting_by_type" -> GroupCountingByType.serializer().deserialize(decoder)
            "help" -> Help.serializer().deserialize(decoder)
            "info" -> Info.serializer().deserialize(decoder)
            "remove_by_id" -> RemoveById.serializer().deserialize(decoder)
            "remove_first" -> RemoveFirst.serializer().deserialize(decoder)
            "remove_lower" -> RemoveLower.serializer().deserialize(decoder)
            "show" -> Show.serializer().deserialize(decoder)
            "sum_of_engine_power" -> SumOfEnginePower.serializer().deserialize(decoder)
            "update" -> Update.serializer().deserialize(decoder)
            else -> throw UnexpectedCommandException()
        }
        return command
    }
}