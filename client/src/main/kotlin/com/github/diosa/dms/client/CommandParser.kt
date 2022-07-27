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
            "Registry" -> SignUp.serializer().deserialize(decoder)
            "Log in" -> LogIn.serializer().deserialize(decoder)
            "Add" -> Add.serializer().deserialize(decoder)
            "Add if min" -> AddIfMin.serializer().deserialize(decoder)
            "Clear" -> Clear.serializer().deserialize(decoder)
            "Count by type" -> CountByType.serializer().deserialize(decoder)
            "Execute script" -> ExecuteScript.serializer().deserialize(decoder)
            "Exit" -> Exit.serializer().deserialize(decoder)
            "Group counting by type" -> GroupCountingByType.serializer().deserialize(decoder)
            "Help" -> Help.serializer().deserialize(decoder)
            "Info" -> Info.serializer().deserialize(decoder)
            "Remove by id" -> RemoveById.serializer().deserialize(decoder)
            "Remove first" -> RemoveFirst.serializer().deserialize(decoder)
            "Remove lower" -> RemoveLower.serializer().deserialize(decoder)
            "Show" -> Show.serializer().deserialize(decoder)
            "Sum of engine power" -> SumOfEnginePower.serializer().deserialize(decoder)
            "Update" -> Update.serializer().deserialize(decoder)
            else -> throw UnexpectedCommandException()
        }
        return command
    }
}