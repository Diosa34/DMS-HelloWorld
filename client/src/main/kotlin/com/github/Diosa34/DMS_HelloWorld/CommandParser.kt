package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractStringReader
import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.commands.*
import com.github.Diosa34.DMS_HelloWorld.exceptions.ParseException
import com.github.Diosa34.DMS_HelloWorld.exceptions.UnexpectedCommandException
import com.github.Diosa34.DMS_HelloWorld.io.FileStringReader
import com.github.Diosa34.DMS_HelloWorld.serialize.CompositeConsoleDecoderDelegate
import com.github.Diosa34.DMS_HelloWorld.serialize.CompositeFileDecoderDelegate
import com.github.Diosa34.DMS_HelloWorld.serialize.GeneralDecoder
import kotlinx.serialization.encoding.CompositeDecoder
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
    fun parse(logger: Logger, str: String, attempts: Int, stringReader: AbstractStringReader): BoundCommand {
        val request = str.trim().split(Regex("""\s+"""))
        if (request.isEmpty()){
            throw UnexpectedCommandException()
        }

        var decoder: CompositeDecoder = CompositeConsoleDecoderDelegate(logger, attempts, stringReader)
        if (stringReader is FileStringReader) {
            decoder = CompositeFileDecoderDelegate(logger, attempts, stringReader)
        }

        val command: BoundCommand = when (request[0]) {
            "registry" -> Register.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "log_in" -> LogIn.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "add" -> Add.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "add_if_min" -> AddIfMin.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "clear" -> Clear.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "count_by_type" -> CountByType.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "execute_script" -> ExecuteScript.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "exit" -> Exit.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "group_counting_by_type" -> GroupCountingByType.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "help" -> Help.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "info" -> Info.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "remove_by_id" -> RemoveById.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "remove_first" -> RemoveFirst.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "remove_lower" -> RemoveLower.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "show" -> Show.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "sum_of_engine_power" -> SumOfEnginePower.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            "update" -> Update.serializer().deserialize(GeneralDecoder(attempts, stringReader, logger, decoder))
            else -> throw UnexpectedCommandException()
        }
        return command
    }
}