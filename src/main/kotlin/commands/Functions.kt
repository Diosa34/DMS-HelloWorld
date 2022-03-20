package commands

import classes.Coordinates

import classes.Vehicle
import enums.FuelType
import enums.VehicleType



fun addCall(args: Array<String>) {
    information["add"] // как вызвать эту штуку
}


fun <T: Any> tryGet(field: String, t: Int, message: String, number: String.() -> T?) : T? {
    for(i in 0 until t) {
        if(i == 0){
            field.number().let{ number ->
                if(number == null){
                    println("Данные некорректны")
                    println(message)
                    println("Количество оставшихся попыток: ${t-i-1}")
                }
                else return@tryGet number
        }
        } else{
            readln().number().let{ number ->
                if(number == null){
                    println("Данные некорректны")
                    println(message)
                    println("Количество оставшихся попыток: ${t-i-1}")
                }
                else return@tryGet number
            }
        }
    }
    println("Вы истратили все попытки")
    return null
}

// как использовать эту функцию, если tryGet() должен проверять не readln(), а известное значение
fun instanceCreate(type: String, name: String, x: String, y: String, enginePower: String, fuel: String, t: Int) : Vehicle? {

    // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
    val type: VehicleType = tryGet(type, t, "Введите число от 0 до 2") {
        toIntOrNull()?.let(VehicleType::getVehicle)
    } ?: return@instanceCreate null


    // isBlank() - строка, состоящая из пробельных символов
    val name = tryGet(name, t, "Марка не может быть пустой строкой") { takeIf { isNotBlank() } } ?: return@instanceCreate null


    val x: Float = tryGet(x, t, "Введите число в десятичных дробях через точку, без лишних символов")
    { toFloatOrNull() } ?: return@instanceCreate null


    val y: Int = tryGet(y, t, "Введите число от 0 до 2") { toIntOrNull() } ?: return@instanceCreate null


    val enginePower: Float = tryGet(enginePower, t, "Мощность должна быть положительным дробным числом") {
        toFloatOrNull()?.takeIf { number -> number > 0 }
    } ?: return@instanceCreate null


    // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
    val fuel: FuelType = tryGet(fuel, t, "Введите число от 0 до 2:") {
        toIntOrNull()?.let(FuelType::getFuel)
    } ?: return@instanceCreate null

    return Vehicle(name, Coordinates(x, y), enginePower, type, fuel)
}

fun instanceCreate(attempts: Int) : Vehicle? {
    if (attempts > 1){}
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
