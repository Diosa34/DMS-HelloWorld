package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.classes.Coordinates
import com.github.Diosa34.DMS_HelloWorld.classes.Vehicle
import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.enums.FuelType
import com.github.Diosa34.DMS_HelloWorld.enums.VehicleType
import java.time.DateTimeException
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeParseException
import java.time.zone.ZoneRulesException

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
                if (i != t-1) {
                    println(message)
                    println("Количество оставшихся попыток: ${t-i-1}")
                } else {
                    println("Попытки закончились")
                    println("Выполнение команды завершено")
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
fun instanceCreate(id: String, creationTime: String, type: String, name: String, x: String, y: String, enginePower: String, fuel: String, t: Int) : Vehicle? {

    // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
    val type: VehicleType = tryGet(type, t, "В качестве типа средства передвижения введите число от 0 до 2") {
        toIntOrNull()?.let(VehicleType::getVehicle)
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
    val fuel: FuelType = tryGet(fuel, t, "В качестве типа топлива введите число от 0 до 2:") {
        toIntOrNull()?.let(FuelType::getFuel)
    } ?: return@instanceCreate null

    val id: Int = tryGet(id, t, "ID должно быть любым целым числом, кроме ${CollectionOfVehicles.globalCollection.map { it.id }}") {
        toIntOrNull().takeIf { !CollectionOfVehicles.globalCollection.map { it.id }.contains(toIntOrNull())}
    } ?: return@instanceCreate null

    val time: ZonedDateTime = tryGet(creationTime, t, "Тег <creationDate> должен содержать правильное имя" +
            " часового пояса в формате Continent/Region, например, Europe/Moscow") {
        try {
            Instant.now().atZone(ZoneId.of((creationTime.split(" ")).joinToString ("/")))
        } catch (ex: DateTimeParseException) {
            return@tryGet null
        } catch (ex: ZoneRulesException) {
            return@tryGet null
        } catch (ex: DateTimeException) {
            return@tryGet null
        }
    } ?: return@instanceCreate null

    return Vehicle(id, name, Coordinates(x, y), time, enginePower, type, fuel)
}

/**
 * Verification the validity of an argument execution сonstructor for creating an instance based on console
 */
fun instanceCreate(attempts: Int) : Vehicle? {
    println("Введите номер соответствующего типа средства передвижения из предложенных:")
    println(VehicleType.getTypes())
    val type: VehicleType = tryGet(readln(), attempts, "Введите число от 0 до 2") {
        toIntOrNull()?.let(VehicleType::getVehicle)
    } ?: return@instanceCreate null

    println("Введите марку средства передвижения")
    // isBlank() - строка, состоящая из пробельных символов
    val name = tryGet(readln(), attempts, "Имя не может быть пустой строкой") {
        takeIf { isNotBlank() } } ?: return@instanceCreate null

    println("Введите координаты")
    println("Введите координату X в виде десятичной дроби через точку:")
    val x: Float = tryGet(readln(), attempts, "Введите число в десятичных дробях через точку, без лишних символов")
    { toFloatOrNull() } ?: return@instanceCreate null

    println("Введите координату Y - целое число:")
    val y: Int = tryGet(readln(), attempts, "Введите целое число") { toIntOrNull() } ?: return@instanceCreate null

    println("Введите мощность двигателя в десятичных дробях")
    val enginePower: Float = tryGet(readln(), attempts, "Мощность должна быть положительным дробным числом") {
        toFloatOrNull()?.takeIf { number -> number > 0 }
    } ?: return@instanceCreate null

    println("Введите номер соответствующего типа топлива из предложенных:")
    println(FuelType.getTypes())
    // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
    val fuel: FuelType = tryGet(readln(), attempts, "Введите число от 0 до 2:") {
        toIntOrNull()?.let(FuelType::getFuel)
    } ?: return@instanceCreate null

    println("Элемент успешно добавлен или обновлён")
    return Vehicle(name, Coordinates(x, y), enginePower, type, fuel)
}
