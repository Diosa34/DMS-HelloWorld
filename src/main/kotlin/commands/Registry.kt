package commands

import collection.CollectionOfVehicles
import com.github.Diosa34.ObjectConverter.Converter

import enums.InstanceCreator
import enums.VehicleType
import parsing.RequestsScanner
import java.io.*
import java.util.*
import kotlin.system.exitProcess


// для глобальных переменных не нужно создавать класс
internal val information: Map<String, Command> = mapOf(      // :: перед названием класса возвращают его конструктор
    "help" to Command(
        "help",
        "вывести справку по доступным командам",
        ::forHelp
    ), // слово to создаёт из своих операндов пару

    "info" to Command("info", "вывести информацию о коллекции") { _, _, _, _ ->
        println("Тип: средства передвижения")
        println("Количество элементов: ${CollectionOfVehicles.globalCollection!!.size}")
        if (CollectionOfVehicles.globalCollection!!.size > 0) {
            val minDate = CollectionOfVehicles.globalCollection.minOf { elem -> elem.creationDate }
            println("Дата инициализации: $minDate")
        } else {
            println("Коллекция не проинициализирована")
        }
    },

    "show" to Command("show", "вывести все элементы коллекции") { _, _, _, _->
        if (CollectionOfVehicles.globalCollection!!.size > 0) {
            for (elem in CollectionOfVehicles.globalCollection) {
                println(elem.toString())
            }
        } else {
            println("Коллекция пуста")
        }

    },

    "add" to Command("add", "добавить новый элемент в коллекцию") { _, _, creator, scanner ->
        CollectionOfVehicles.globalCollection.add(creator.invoke(scanner))
    },

    "update" to Command(
        "update",
        "обновить значение элемента коллекции, номер которого равен заданному"
    ) { args, attempts, creator, scanner ->
        val id: Int =
            tryGet(args[1], attempts, "Введите одно из чисел ${CollectionOfVehicles.globalCollection.map { it.id }}") {
                toIntOrNull()
            } ?: return@Command
        for (elem in CollectionOfVehicles.globalCollection) {
            if (elem.id == id) {
                CollectionOfVehicles.globalCollection[CollectionOfVehicles.globalCollection.indexOf(elem)] =
                    creator.invoke(scanner)
            }
        }
    },

    "remove_by_id" to Command("remove_by_id", "удалить элемент из коллекции по его номеру") { args, attempts, _, _ ->
        val id: Int =
            tryGet(args[1], attempts, "Введите одно из чисел ${CollectionOfVehicles.globalCollection.map { it.id }}") {
                toIntOrNull()
            } ?: return@Command
        if (CollectionOfVehicles.globalCollection!!.size == 0) {
            println("Коллекция пуста")
            return@Command
        } else if (CollectionOfVehicles.globalCollection.any { it.id == id }) {
            CollectionOfVehicles.globalCollection.removeIf {
                it.id == id
            }
            println("Элемент удалён")
        } else if (CollectionOfVehicles.globalCollection.none { it.id == id }) {
            println("Элемент с id $id не найден")
        }
    },

    "clear" to Command("clear", "очистить коллекцию") { _, _, _, _ ->
        if (CollectionOfVehicles.globalCollection!!.size > 0) {
            CollectionOfVehicles.globalCollection.clear()
            println("Коллекция очищена")
        } else {
            println("Коллекция пуста")
        }

    },

    "save" to Command("save", "сохранить коллекцию в файл") { args, attempts, creator, scanner ->
        val converter = Converter("MyXML.xml")

        /** Writing converted data to a file {@link Converter#xmlInitialization(Convertible, Integer)}*/
        /** Writing converted data to a file [Converter.xmlInitialization] */
        converter.xmlInitialization(CollectionOfVehicles.globalCollection, 0)
        println("Коллекция успешно сохранена")
    },

    "execute_script" to Command("execute_script", "считать и исполнить скрипт из указанного файла") { args, _, _, _ ->
        try {
            val newScanner = RequestsScanner(FileInputStream(File(args[1])))
            newScanner.makeRequest(1, InstanceCreator.CREATE_FROM_FILE, newScanner)
        } catch (ex: FileNotFoundException) {
            println("Файл не найден")
        }
    },

    "exit" to Command("exit", "завершить программу (без сохранения в файл)") { _, _, _, _ ->
        exitProcess(0)
    },

    "remove_first" to Command("remove_first", "удалить первый элемент из коллекции") { _, _, _, _ ->
        if (CollectionOfVehicles.globalCollection!!.size > 0) {
            CollectionOfVehicles.globalCollection.removeFirst()
            println("Первый элемент удалён")
        } else {
            println("Коллекция пуста")
        }
    },

    "add_if_min" to Command(
        "add_if_min", "добавить новый элемент в коллекцию, если его значение меньше," +
                " чем у наименьшего элемента этой коллекции (элементы сравниваются по длине марки средства передвижения)"
    ) { _, attempts, creator, scanner ->
        println("Введите марку средства передвижения")
        val name = tryGet(scanner.nextLine(), attempts, "Имя не может быть пустой строкой") { takeIf { isNotBlank() } } ?: return@Command
        val minElem = CollectionOfVehicles.globalCollection.minOf { it.name.length }
        if (name.length < minElem) {
            CollectionOfVehicles.globalCollection.add(creator.invoke(scanner))
        } else {
            println("Найдены элементы с более коротким названием")
        }
    },

    "remove_lower" to Command(
        "remove_lower", "удалить из коллекции все элементы, меньшие, чем" +
                " заданный (элементы сравниваются по длине марки средства передвижения)"
    ) { _, attempts, _, scanner ->
        if (CollectionOfVehicles.globalCollection!!.size == 0) {
            println("Коллекция пуста")
        } else {
            println("Введите марку средства передвижения")
            val name = tryGet(scanner.nextLine(), attempts, "Имя не может быть пустой строкой") { takeIf { isNotBlank() } }
                ?: return@Command
            if (CollectionOfVehicles.globalCollection.any { it.name.length < name.length }) {
                CollectionOfVehicles.globalCollection.removeIf { elem ->
                    elem.name.length < name.length
                }
                println("Элементы удалены")
            } else {
                println("Элементов с более короткой маркой не найдено")
            }
        }
    },

    "sum_of_engine_power" to Command("sum_of_engine_power", "вывести сумму значений мощностей двигателей всех элементов") { _, _, _, _ ->
        var summa = 0.0
        for (elem in CollectionOfVehicles.globalCollection) {
            summa += elem.enginePower
        }
        println(summa)
        if (CollectionOfVehicles.globalCollection!!.size > 0) {
            println("Коллекция пуста")
        }
    },

    "group_counting_by_type" to Command("group_counting_by_type", "сгруппировать элементы коллекции по значению типа средства передвижения, вывести количество элементов в каждой группе"
    ) { _, _, _, _ ->
        val countOfCar = CollectionOfVehicles.globalCollection.count {
            it.type == VehicleType.CAR
        }
        val countOfSubmarine = CollectionOfVehicles.globalCollection.count {
            it.type == VehicleType.SUBMARINE
        }
        val countOfShip = CollectionOfVehicles.globalCollection.count {
            it.type == VehicleType.SHIP
        }
        println("$countOfCar - количество машин")
        println("$countOfSubmarine - количество подводных лодок")
        println("$countOfShip - количество кораблей")
    },

    "count_by_type" to Command(
        "count_by_type", "вывести количество элементов, значение типа которых равно заданному"
    ) { _, attempts, _, scanner ->
        println(VehicleType.getTypes())
        // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
        val type: VehicleType =
            tryGet(scanner.nextLine(), attempts, "Введите номер соответствующего типа средства передвижения из предложенных") {
                toIntOrNull()?.let(VehicleType::getVehicle)
            } ?: return@Command
        val count = CollectionOfVehicles.globalCollection.count {
            it.type == type
        }
        println(count)
    }
)

private fun forHelp(requestString: Array<String>, attempts: Int, creator: InstanceCreator, scanner: Scanner) {
    for ((name, command) in information) {
        println("$name - ${command.help}")
    }
}

