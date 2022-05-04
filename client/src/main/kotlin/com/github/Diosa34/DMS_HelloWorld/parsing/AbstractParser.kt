package com.github.Diosa34.DMS_HelloWorld.parsing

import com.github.Diosa34.DMS_HelloWorld.*
import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.commands.tryGet
import com.github.Diosa34.DMS_HelloWorld.enums.InstanceCreator
import com.github.Diosa34.DMS_HelloWorld.enums.VehicleType
import kotlin.jvm.Throws

/**
 * Abstract class for any parser
 */
object AbstractParser{
    private val globalArgs: Application
    /**
     * return Pair<Command, Array<String>>? where Command is name and Array<String>>? is nullable attributes
     */
    @JvmStatic
    @Throws(UnexpectedCommandException::class, ParseException::class)
    fun parse(str: String, attempts: Int, creator: InstanceCreator, stringReader: AbstractStringReader): BoundCommand {
        val request = str.trim().split(Regex("""\s+"""))
        if (request.isEmpty()){
            throw UnexpectedCommandException()
        }
        val args = request.slice(1 until request.size).toTypedArray()
        val command: BoundCommand = when (request[0]) {
            "add" -> {
                Add(creator.invoke(stringReader) ?: throw NullPointerException())
            }
            "add_if_min" -> AddIfMin(attempts, creator, stringReader)
            "clear" -> Clear
            "count_by_type" -> {
                val changableId = if (args.isEmpty()){
                    println("Введите номер соответствующего типа средства передвижения из предложенных")
                    println(VehicleType.getTypes())
                    stringReader.getNextLine()
                } else {
                    args[0]
                }
                // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
                val type: VehicleType =
                    tryGet(changableId, attempts, "Введите номер соответствующего типа средства" +
                            " передвижения из предложенных") {
                        toIntOrNull()?.let(VehicleType::getVehicle)
                    } ?: throw ParseException()
                CountByType(type)
            }
            "execute_script" -> {
                val path = if (args.size < 2){
                    println("Введите id элемента, который хотите удалить")
                    stringReader.getNextLine()
                } else {
                    args[0]
                }
                ExecuteScript(path, globalArgs)
            }
            "exit" -> Exit
            "group_counting_by_type" -> GroupCountingByType
            "help" -> Help
            "info" -> Info
            "remove_by_id" -> {
                val changableId = if (args.size < 2){
                    println("Введите id элемента, который хотите удалить")
                    stringReader.getNextLine()
                } else {
                    args[0]
                }
                val id: Int =
                    tryGet(changableId, attempts, "Введите одно из чисел" +
                            " ${CollectionOfVehicles.globalCollection.map { it.id }}") {
                        toIntOrNull()
                    } ?: throw ParseException()
                RemoveById(id)
            }
            "remove_first" -> RemoveFirst
            "remove_lower" -> {
                println("Введите название средства передвижения")
                val name = tryGet(
                    stringReader.getNextLine(),
                    attempts,
                    "Имя не может быть пустой строкой"
                ) { takeIf { isNotBlank() } }
                    ?: throw ParseException()
                RemoveLower(name)
            }
            "save" -> Save(stringReader, globalArgs)
            "show" -> Show
            "sum_of_engine_power" -> SumOfEnginePower
            "update" -> {
                val chanId = if (args.isEmpty()){
                    println("Введите id элемента, который хотите обновить")
                    stringReader.getNextLine()
                } else {
                    args[0]
                }
                val id: Int =
                    tryGet(chanId, attempts, "Введите одно из чисел" +
                            " ${CollectionOfVehicles.globalCollection.map { it.id }}") {
                        toIntOrNull()
                    } ?: throw ParseException()
                Update(id, creator, stringReader)
            }
            else -> throw UnexpectedCommandException()
        }
        return command
    }
}