package commands

import enums.InstanceCreator
import java.util.*

/**
 * Entity describing the command
 */
class Command(
        val name: String,
        val help: String,
        private val executeFun: (Array<String>, Int, InstanceCreator, Scanner) -> Unit, // тип функции, которая принимает массив строк и возвращает Unit(void в джаве)
) {
    fun execute(requestString: Array<String>, attempts: Int, creator: InstanceCreator, scanner: Scanner) =
        executeFun(requestString, attempts, creator, scanner)

// в котлине нет слова static, поэтому все статические члены выносятся в companion object
    companion object {
        @JvmField // доступ из джавы без геттера
//      @JvmStatic делает переменную статической не только в котлине, но и в джаве
        val registry = information
    }
}
