package commands

import classes.VehicleType
import collection.CollectionOfVehicles
import java.io.*

// для глобальных переменных не нужно создавать класс
internal val information: Map<String, Command> = mapOf(      // :: перед названием класса возвращают его конструктор
        "help" to Command("help", "вывести справку по доступным командам", ::forHelp), // слово to создаёт из своих операндов пару

        "info" to Command("info", "вывести информацию о коллекции") { args ->
            println("Тип: средства передвижения")
            println("Количество элементов: ${CollectionOfVehicles.globalCollection!!.size}")
            val minDate = CollectionOfVehicles.globalCollection.minOf { elem -> elem.creationDate }
            println("Дата инициализации: $minDate")
        },

        "show" to Command("show", "вывести все элементы коллекции") { args ->
            for (elem in CollectionOfVehicles.globalCollection) {
                println(elem.toString())
            }
        },

        "add" to Command("add", "добавить новый элемент в коллекцию") { args ->
            CollectionOfVehicles.globalCollection.add(instanceCreate(3))
        },

        "update" to Command("update", "обновить значение элемента коллекции, номер которого равен заданному") { args ->
            val id: Int = args[1].toIntOrNull() ?:
                    tryGet(2, "введите число от 0 до ${CollectionOfVehicles.globalCollection!!.size - 1}") {
                        toIntOrNull()} ?: return@Command
            for(elem in CollectionOfVehicles.globalCollection) {
                if (elem.id == id) {
                    CollectionOfVehicles.globalCollection[CollectionOfVehicles.globalCollection.indexOf(elem)] = instanceCreate(3)
                }
            }
        },

        "remove_by_id" to Command("remove_by_id", "удалить элемент из коллекции по его номеру") { args ->
            val id: Int = args[1].toIntOrNull() ?:
            tryGet(2, "введите число от 0 до ${CollectionOfVehicles.globalCollection!!.size - 1}") {
                toIntOrNull()} ?: return@Command
            CollectionOfVehicles.globalCollection.removeIf { elem ->
                elem.id == id
            }
        },

        "clear" to Command("clear", "очистить коллекцию") { args ->
            CollectionOfVehicles.globalCollection.clear()
        },

        "save" to Command("save", "сохранить коллекцию в файл") { args ->
            TODO()
        },

        "execute_script" to Command("execute_script", "считать и исполнить скрипт из указанного файла") { args ->
            val scanner = Scanner(FileInputStream(File(args[1])))
            scanner.makeRequest()
        },

        "exit" to Command("exit", "завершить программу (без сохранения в файл)") { args ->
            System.exit(0)
        },

        "remove_first" to Command("remove_first", "удалить первый элемент из коллекции") { args ->
            CollectionOfVehicles.globalCollection.removeFirst()
        },

        "add_if_min" to Command("add_if_min", "добавить новый элемент в коллекцию, если его значение меньше," +
                " чем у наименьшего элемента этой коллекции") { args ->
            println("Введите название средства передвижения")
            val name = tryGet(3, "Имя не может быть пустой строкой") { takeIf { isNotBlank() } } ?: return@Command
            val minElem = CollectionOfVehicles.globalCollection.minOf { elem -> elem.name.length }
            if (name.length < minElem) {
                CollectionOfVehicles.globalCollection.add(instanceCreate(3))
            }
        },

        "remove_lower" to Command("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный") { args ->
            println("Введите название средства передвижения")
            val name = tryGet(3, "Имя не может быть пустой строкой") { takeIf { isNotBlank() } } ?: return@Command
            CollectionOfVehicles.globalCollection.removeIf { elem ->
                elem.name.length < name.length
            }
        },

        "sum_of_engine_power" to Command("sum_of_engine_power", "вывести сумму значений мощностей" +
                " двигателей всех элементов") { args ->
            var summa = 0.0
            for (elem in CollectionOfVehicles.globalCollection) {
                summa += elem.enginePower
            }
            println(summa)
        },

        "group_counting_by_type" to Command("group_counting_by_type", "сгруппировать элементы коллекции" +
                " по значению типа средства передвижения, вывести количество элементов в каждой группе") { args ->
            val countOfCar = CollectionOfVehicles.globalCollection.filter { elem ->
                elem.type == VehicleType.CAR
            }.count()
            val countOfSubmarine = CollectionOfVehicles.globalCollection.filter { elem ->
                elem.type == VehicleType.SUBMARINE
            }.count()
            val countOfShip = CollectionOfVehicles.globalCollection.filter { elem ->
                elem.type == VehicleType.SHIP
            }.count()
            println("$countOfCar - количество машин")
            println("$countOfSubmarine - количество подводных лодок")
            println("$countOfShip - количество кораблей")
        },

        "count_by_type" to Command("count_by_type", "вывести количество элементов, значение типа" +
                " которых равно заданному") { args ->
            println(VehicleType.getTypes())
            // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
            val type: VehicleType = tryGet(3, "Введите номер соответствующего типа средства передвижения из предложенных") {
                toIntOrNull()?.let(VehicleType::getVehicle)
            } ?: return@Command
            val count = CollectionOfVehicles.globalCollection.filter { elem ->
                elem.type == type
            }.count()
            println(count);
        }
)

private fun forHelp(args: Array<String>) {
    for ((name, command) in information) {
        println("$name - ${command.help}")
    }
}

