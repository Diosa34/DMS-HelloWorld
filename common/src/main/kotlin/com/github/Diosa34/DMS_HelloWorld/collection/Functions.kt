package com.github.Diosa34.DMS_HelloWorld.collection

import com.github.Diosa34.DMS_HelloWorld.Coordinates
import com.github.Diosa34.DMS_HelloWorld.Vehicle
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger

/**
 * Verification the validity of an argument
 */
fun <T: Any> tryGet(field: String, t: Int, message: String, number: String.() -> T?) : T? {
    for(i in 0 until t) {
        var data = field
        if(i != 0){
            data = readln()
        }
        data.number().let{ number ->
            if(number == null){
                println("Данные некорректны")
                println(message)
                if (i != t-1) {
                    println("Количество оставшихся попыток: ${t-i-1}")
                }
            }
            else return@tryGet number
        }
    }
    return null
}

/**
 * Verification the validity of an argument execution сonstructor for creating an instance based on file
 */
@Suppress("NAME_SHADOWING")
fun instanceCreate(name: String, x: String, y: String,
                   enginePower: String, type: String, fuel: String, t: Int) : Vehicle? {

    // ?. - функция или поле берётся, если слева НЕ null, в противном случае результат выражения null
    // ?: - функция или поле берётся, если слева null, в противном случае берётся левая часть и кастуется к NotNull
    val type: VehicleType = tryGet(type, t, "В качестве типа средства передвижения введите одно из " +
            "названий ${VehicleType.getTypes().values}") {
        if ( VehicleType.stringToType.containsKey(this) ) {
            VehicleType.stringToType[this]
        } else {
            null
        }
    } ?: return@instanceCreate null


    // isBlank() - строка, состоящая из пробельных символов
    val name = tryGet(name, t, "Марка не может быть пустой строкой") { takeIf { isNotBlank() } } ?: return@instanceCreate null


    val x: Float = tryGet(x, t, "Координата Х должна быть числом в десятичных дробях через точку без лишних символов")
    { toFloatOrNull() } ?: return@instanceCreate null


    val y: Int = tryGet(y, t, "Координата Y должна быть целым числом") { toIntOrNull() } ?: return@instanceCreate null


    val enginePower: Float = tryGet(enginePower, t, "Мощность должна быть положительным дробным числом") {
        toFloatOrNull()?.takeIf { number -> number > 0 }
    } ?: return@instanceCreate null


    // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
    val fuel: FuelType = tryGet(fuel, t, "В качестве типа топлива введите введите одно из " +
            "названий ${FuelType.getTypes().values}") {
        if ( FuelType.stringToType.containsKey(this) ) {
            FuelType.stringToType[this]
        } else {
            null
        }
    } ?: return@instanceCreate null

//    val id: Int = tryGet(id, t, "Элемент с таким id уже есть в коллекции") {
//        toIntOrNull()?.takeIf { !CollectionInMemory.collection.map { it.id }.contains(toIntOrNull())}
//    } ?: return@instanceCreate null
//
//    val time: ZonedDateTime = tryGet(creationTime, t, "Тег <creationDate> должен содержать правильное имя" +
//            " часового пояса в формате Continent/Region, например, Europe/Moscow") {
//        var creationTime1 = this
//        if (!creationTime1.startsWith("[")){
//            creationTime1 = creationTime1.substring(creationTime1.indexOf("[")+1,creationTime1.indexOf("]"))
//        } else {
//            creationTime1 = creationTime
//        }
//        try {
//            Instant.now().atZone(ZoneId.of((creationTime1.split(" ")).joinToString ("/")))
//        } catch (ex: DateTimeParseException) {
//            return@tryGet null
//        } catch (ex: ZoneRulesException) {
//            return@tryGet null
//        } catch (ex: DateTimeException) {
//            return@tryGet null
//        }
//    } ?: return@instanceCreate null

    return Vehicle(
        name,
        Coordinates(x, y),
        enginePower,
        type,
        fuel,
        author
    )
}

/**
 * Verification the validity of an argument execution сonstructor for creating an instance based on console
 */
fun instanceCreate(logger: Logger, attempts: Int) : Vehicle? {
    logger.print("Введите номер соответствующего типа средства передвижения из предложенных:")
    logger.print(VehicleType.getTypes().toString())
    val type: VehicleType = tryGet(readln(), attempts, "Введите число от 0 до 2") {
        toIntOrNull()?.let(VehicleType.Companion::getVehicle)
    } ?: return@instanceCreate null

    logger.print("Введите марку средства передвижения")
    // isBlank() - строка, состоящая из пробельных символов
    val name = tryGet(readln(), attempts, "Имя не может быть пустой строкой") {
        takeIf { isNotBlank() } } ?: return@instanceCreate null

    logger.print("Введите координаты")
    logger.print("Введите координату X в виде десятичной дроби через точку:")
    val x: Float = tryGet(readln(), attempts, "Введите число в десятичных дробях через точку, без лишних символов")
    { toFloatOrNull() } ?: return@instanceCreate null

    logger.print("Введите координату Y - целое число:")
    val y: Int = tryGet(readln(), attempts, "Введите целое число") { toIntOrNull() } ?: return@instanceCreate null

    logger.print("Введите мощность двигателя в десятичных дробях")
    val enginePower: Float = tryGet(readln(), attempts, "Мощность должна быть положительным дробным числом") {
        toFloatOrNull()?.takeIf { number -> number > 0 }
    } ?: return@instanceCreate null

    logger.print("Введите номер соответствующего типа топлива из предложенных:")
    logger.print(FuelType.getTypes().toString())
    // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
    val fuel: FuelType = tryGet(readln(), attempts, "Введите число от 0 до 2:") {
        toIntOrNull()?.let(FuelType.Companion::getFuel)
    } ?: return@instanceCreate null

    return Vehicle(
        name,
        Coordinates(x, y),
        enginePower,
        type,
        fuel,
        author
    )
}
