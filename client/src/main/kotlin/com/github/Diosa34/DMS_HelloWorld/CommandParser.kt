package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractStringReader
import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.collection.InstanceCreator
import com.github.Diosa34.DMS_HelloWorld.collection.VehicleType
import com.github.Diosa34.DMS_HelloWorld.collection.tryGet
import com.github.Diosa34.DMS_HelloWorld.commands.*
import com.github.Diosa34.DMS_HelloWorld.exceptions.ParseException
import com.github.Diosa34.DMS_HelloWorld.exceptions.UnexpectedCommandException
import java.io.FileNotFoundException
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
    fun parse(logger: Logger, str: String, attempts: Int, creator: InstanceCreator, stringReader: AbstractStringReader,
              log: java.util.logging.Logger): BoundCommand {
        val request = str.trim().split(Regex("""\s+"""))
        if (request.isEmpty()){
            throw UnexpectedCommandException()
        }
        val args = request.slice(1 until request.size).toTypedArray()
        val command: BoundCommand = when (request[0]) {
            "add" -> {
                Add(creator.invoke(stringReader) ?: throw ParseException())
            }
            "add_if_min" -> {
                logger.print("Введите марку средства передвижения")
                val name = tryGet(stringReader.getNextLine(), attempts, "Имя не может быть пустой строкой")
                { takeIf { isNotBlank() } } ?: throw ParseException()
                AddIfMin(name, creator.invoke(stringReader) ?: throw ParseException())
            }
            "clear" -> Clear
            "count_by_type" -> {
                val changableId = if (args.isEmpty()){
                    logger.print("Введите номер соответствующего типа средства передвижения из предложенных")
                    logger.print(VehicleType.getTypes().toString())
                    stringReader.getNextLine()
                } else {
                    args[0]
                }
                // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
                val type: VehicleType =
                    tryGet(changableId, attempts, "Введите номер соответствующего типа средства" +
                            " передвижения из предложенных") {
                        toIntOrNull()?.let(VehicleType.Companion::getVehicle)
                    } ?: throw ParseException()
                CountByType(type)
            }
            "execute_script" -> {
                val path = if (args.isEmpty()) {
                    logger.print("Введите путь к файлу, который хотите прочитать")
                    stringReader.getNextLine()
                } else {
                    args[0]
                }
                try {
                if (FileVerification.fullVerification(path)) {
                    HistoryOfExecutingScripts.addScript(logger, path)
                }
                } catch (ex: FileNotFoundException) {
                    println(ex.message)
                } catch (ex: FileVerificationException) {
                    println(ex.message)
                }
                ExecuteScript(path, log)
            }
            "exit" -> Exit
            "group_counting_by_type" -> GroupCountingByType
            "help" -> Help
            "info" -> Info
            "remove_by_id" -> {
                val changableId = if (args.size < 2){
                    logger.print("Введите id элемента, который хотите удалить")
                    stringReader.getNextLine()
                } else {
                    args[0]
                }
                val id: Int =
                    tryGet(changableId, attempts, "Введите число") {
                        toIntOrNull()
                    } ?: throw ParseException()
                RemoveById(id)
            }
            "remove_first" -> RemoveFirst
            "remove_lower" -> {
                logger.print("Введите название средства передвижения")
                val name = tryGet(
                    stringReader.getNextLine(),
                    attempts,
                    "Имя не может быть пустой строкой"
                ) { takeIf { isNotBlank() } }
                    ?: throw ParseException()
                RemoveLower(name)
            }
            "show" -> Show
            "sum_of_engine_power" -> SumOfEnginePower
            "update" -> {
                val chanId = if (args.isEmpty()){
                    logger.print("Введите id элемента, который хотите обновить")
                    stringReader.getNextLine()
                } else {
                    args[0]
                }
                val id: Int =
                    tryGet(chanId, attempts, "Введите число") {
                        toIntOrNull()
                    } ?: throw ParseException()
                Update(id, creator.invoke(stringReader) ?: throw ParseException())
            }
            else -> throw UnexpectedCommandException()
        }
        return command
    }
}