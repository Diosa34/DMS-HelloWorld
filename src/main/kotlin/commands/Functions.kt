package commands

import classes.Coordinates
import classes.FuelType
import classes.Vehicle
import classes.VehicleType

fun addCall(args: Array<String>) {
    information["add"] // как вызвать эту штуку
}


fun <T: Any> tryGet(t: Int, message: String, number: String.() -> T?) : T? {
    println("У вас есть $t попытки")
    for(i in 0 until t) {
        println(message)
        readln().number().let{ number ->
            if(number == null){
                println("Данные некорректны")
            }
            else return@tryGet number
        }
    }
    println("Вы истратили все попытки")
    return null
}

// как использовать эту функцию, если tryGet() должен проверять не readln(), а известное значение
fun instanceCreate(t: Int) : Vehicle? {
    println("Введите номер соответствующего типа средства передвижения из предложенных:")
    println(VehicleType.getTypes())
    // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
    val type: VehicleType = tryGet(t, "введите число от 0 до 2") {
        toIntOrNull()?.let(VehicleType::getVehicle)
    } ?: return@instanceCreate null

    println("Введите марку средства передвижения")
    // isBlank() - строка, состоящая из пробельных символов
    val name = tryGet(t, "Имя не может быть пустой строкой") { takeIf { isNotBlank() } } ?: return@instanceCreate null

    println("Введите координаты в десятичных дробях через точку")
    println("Введите координату X в виде десятичной дроби через точку:")
    val x: Float = tryGet(t, "введите число в десятичных дробях через точку, без лишних символов")
    { toFloatOrNull() } ?: return@instanceCreate null

    println("Введите координату Y - целое число:")
    val y: Int = (tryGet(t, "введите число от 0 до 2") { toIntOrNull() } ?: return@instanceCreate null)

    println("Введите мощность двигателя в десятичных дробях")
    val enginePower = tryGet(t, "мощность должна быть положительным дробным числом") {
        toFloatOrNull()?.takeIf { number -> number > 0 }
    } ?: return@instanceCreate null

    println("Введите номер соответствующего типа топлива из предложенных:")
    println(FuelType.getTypes())
    // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
    val fuel: FuelType = tryGet(t, "введите число от 0 до 2") {
        toIntOrNull()?.let(FuelType::getFuel)
    } ?: return@instanceCreate null

    return Vehicle(name, Coordinates(x, y), enginePower, type, fuel)
}

