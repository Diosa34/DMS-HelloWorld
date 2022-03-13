package commands

class Command(
        val name: String,
        val help: String,
        private val executeFun: (Array<String>) -> Unit // тип функции, которая принимает массив строк и возвращает Unit(void в джаве)

) {
    fun execute(requestString: Array<String>) = executeFun(requestString)

// в котлине нет слова static, поэтому все статические члены выносятся в companion object
    companion object {
        @JvmField // доступ из джавы без геттера
//      @JvmStatic делает переменную статической не только в котлине, но и в джаве
        val registry = information
    }
}


//class Example<T: Collection<*>>
//val a: Int
//fun execute(requestString : Array<String>) : Nothing = TODO()