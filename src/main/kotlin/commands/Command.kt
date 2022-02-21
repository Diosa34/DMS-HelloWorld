package commands

import collection.CollectionOfVehicles

/* abstract class Command(
        private val args: Array<String>,

        ) {
    abstract val name: String
    abstract val help: String

    init {
        args!!
    }

    companion object {
        @JvmField // доступ из джавы без геттера
//        @JvmStatic // делает переменную статической не только в котлине, но и в джаве
        val commandsMap = mapOf<
                String,
                (Array<String>) -> Command // тип функции, которая принимает массив строк и возвращает объект класса Command
                >(
                // слово to создаёт из своих операндов пару
                // :: перед названием класса возвращают его конструктор
                "add" to ::Add
                )
    }

    abstract fun execute()
}

 */

class Command(
        val name: String,
        val help: String,
        private val executeFun: (Array<String>) -> Unit // Unit(void в джаве)
) {
    // в котлине нет слова static, поэтому все статические члены выносятся в companion object
    companion object {
        @JvmField
        val registry: Map<String, Command> = mapOf(
                "help" to Command("help", ""){ args ->
                    for ((name, command) in registry)
                },
                "info" to Command("info", ""){args ->
                    println("Тип: средства передвижения")
                    println("Количество элементов: ${CollectionOfVehicles.globalCollection!!.size}")
                    // прочие параметры
                }
        )
    }
    fun execute(requestString : Array<String>) = executeFun(requestString)

}


//class Example<T: Collection<*>>
//val a: Int
//fun execute(requestString : Array<String>) : Nothing = TODO()