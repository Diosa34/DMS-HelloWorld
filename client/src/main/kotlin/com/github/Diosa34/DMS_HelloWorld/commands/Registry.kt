//package com.github.Diosa34.DMS_HelloWorld.commands
//
//import com.github.Diosa34.DMS_HelloWorld.Application
//import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
//import com.github.Diosa34.DMS_HelloWorld.collection.HistoryOfExecutingScripts
//import com.github.Diosa34.DMS_HelloWorld.InstanceCreator
//import com.github.Diosa34.DMS_HelloWorld.VehicleType
//import com.github.Diosa34.DMS_HelloWorld.parsing.*
//import com.github.Diosa34.DMS_HelloWorld.ScannerStringReader
//import com.github.Diosa34.ObjectConverter.Converter
//import java.io.*
//import kotlin.system.exitProcess
//
///**
// * Keys - the name of the com.github.Diosa34.DMS_HelloWorld.commands, values - the implementation of the com.github.Diosa34.DMS_HelloWorld.commands
// */
//// для глобальных переменных не нужно создавать класс
//internal val information: Map<String, ApplicableToCollection> = mapOf(      // :: перед названием класса возвращают его конструктор
//    "help" to com.github.Diosa34.DMS_HelloWorld.Help(), // слово to создаёт из своих операндов пару
//
//    "info" to com.github.Diosa34.DMS_HelloWorld.Info(),
//
//    "show" to BoundCommand("show", "вывести все элементы коллекции") { _, _, _, _, _->
//        if (CollectionOfVehicles.globalCollection!!.size > 0) {
//            for (elem in CollectionOfVehicles.globalCollection) {
//                println(elem.toString())
//            }
//        } else {
//            println("Коллекция пуста")
//        }
//    },
//
//    "add" to BoundCommand("add", "добавить новый элемент в коллекцию") { _, _, creator, scanner, _ ->
//        CollectionOfVehicles.globalCollection.add(creator.invoke(scanner))
//    },
//
//    "update" to BoundCommand(
//        "update",
//        "обновить значение элемента коллекции, номер которого равен заданному"
//    ) { args, attempts, creator, scanner, _ ->
//        if (CollectionOfVehicles.globalCollection!!.size > 0) {
//            val changableId = if (args.size < 1){
//                println("Введите id элемента, который хотите обновить")
//                scanner.getNextLine()
//            } else {
//                args[0]
//            }
//            val id: Int =
//                tryGet(changableId, attempts, "Введите одно из чисел ${CollectionOfVehicles.globalCollection.map { it.id }}") {
//                    toIntOrNull()
//                } ?: return@BoundCommand
//            for (elem in CollectionOfVehicles.globalCollection) {
//                if (elem.id == id) {
//                    CollectionOfVehicles.globalCollection[CollectionOfVehicles.globalCollection.indexOf(elem)] =
//                        creator.invoke(scanner)
//                }
//            }
//        } else {
//            println("Коллекция пуста")
//        }
//    },
//
//    "remove_by_id" to BoundCommand("remove_by_id", "удалить элемент из коллекции по его номеру") { args, attempts, _, scanner, _ ->
//        val changableId = if (args.size < 2){
//            println("Введите id элемента, который хотите удалить")
//            scanner.getNextLine()
//        } else {
//            args[0]
//        }
//        val id: Int =
//            tryGet(changableId, attempts, "Введите одно из чисел ${CollectionOfVehicles.globalCollection.map { it.id }}") {
//                toIntOrNull()
//            } ?: return@BoundCommand
//        if (CollectionOfVehicles.globalCollection!!.size == 0) {
//            println("Коллекция пуста")
//            return@BoundCommand
//        } else if (CollectionOfVehicles.globalCollection.any { it.id == id }) {
//            CollectionOfVehicles.globalCollection.removeIf {
//                it.id == id
//            }
//            println("Элемент удалён")
//        } else if (CollectionOfVehicles.globalCollection.none { it.id == id }) {
//            println("Элемент с id $id не найден")
//        }
//    },
//
//    "clear" to BoundCommand("clear", "очистить коллекцию") { _, _, _, _, _ ->
//        if (CollectionOfVehicles.globalCollection!!.size > 0) {
//            CollectionOfVehicles.globalCollection.clear()
//            println("Коллекция очищена")
//        } else {
//            println("Коллекция пуста")
//        }
//    },
//
//    "save" to BoundCommand("save", "сохранить коллекцию в файл") { _, _, _, scanner, globalArgs ->
//        var filename = globalArgs.filepath
//        if (!FileVerification.fullVerification(filename)) {
//            println("Введите путь к файлу, в который хотите записать коллекцию")
//            filename = scanner.getNextLine()
//            if (!FileVerification.fullVerification(filename)) {
//                return@BoundCommand
//            } else {
//                globalArgs.filepath = filename
//            }
//        }
//        val converter = Converter(filename)
//        converter.xmlInitialization(CollectionOfVehicles.globalCollection, 0)
//        println("Коллекция успешно сохранена")
//    },
//
//    "execute_script" to BoundCommand("execute_script", "считать и исполнить скрипт из указанного файла") { args, _, _, _, globalArgs ->
//        if (args.isNotEmpty()) {
//            if (FileVerification.fullVerification(args[0])) {
//                if (!FileVerification.isSameLinks(File(args[0]).toPath())) {
//                    HistoryOfExecutingScripts.CollectionOfFiles.add(File(args[0]).toPath())
//                    try {
//                        println("Выполнение скрипта: ${File(args[0])}")
//                        val executor = CommandExecutor(
//                            globalArgs.filepath,
//                            ScannerStringReader(FileInputStream(File(args[0])))
//                        )
//                        executor.execute(1, InstanceCreator.CREATE_FROM_FILE)
//                        HistoryOfExecutingScripts.CollectionOfFiles.removeLast()
//                    } catch (ex: FileNotFoundException) {
//                        println("Файл не найден")
//                    }
//                } else {
//                    println("Файл ${File(args[0])} уже исполняется в данный момент.")
//                }
//            }
//        } else {
//            println("Путь к файлу не указан, введите команду заново")
//        }
//    },
//
//    "exit" to BoundCommand("exit", "завершить программу (без сохранения в файл)") { _, _, _, _, _ ->
//        exitProcess(0)
//    },
//
//    "remove_first" to BoundCommand("remove_first", "удалить первый элемент из коллекции") { _, _, _, _, _ ->
//        if (CollectionOfVehicles.globalCollection!!.size > 0) {
//            CollectionOfVehicles.globalCollection.removeFirst()
//            println("Первый элемент удалён")
//        } else {
//            println("Коллекция пуста")
//        }
//    },
//
//    "add_if_min" to BoundCommand(
//        "add_if_min", "добавить новый элемент в коллекцию, если его значение меньше," +
//                " чем у наименьшего элемента этой коллекции (элементы сравниваются по длине марки средства передвижения)"
//    ) { _, attempts, creator, scanner, _ ->
//        println("Введите марку средства передвижения")
//        val name = tryGet(scanner.getNextLine(), attempts, "Имя не может быть пустой строкой") { takeIf { isNotBlank() } } ?: return@BoundCommand
//        if (CollectionOfVehicles.globalCollection!!.size != 0){
//            val minElem = CollectionOfVehicles.globalCollection.sortedWith(compareBy { it.name.length })[0]
//            if (minElem.compareTo(name) > 0) {
//                CollectionOfVehicles.globalCollection.add(creator.invoke(scanner))
//            } else {
//                println("Найдены элементы с таким же по длине или более коротким названием")
//            }
//        } else {
//            println("Коллекция пуста")
//            CollectionOfVehicles.globalCollection.add(creator.invoke(scanner))
//        }
//    },
//
//    "remove_lower" to BoundCommand(
//        "remove_lower", "удалить из коллекции все элементы, меньшие, чем" +
//                " заданный (элементы сравниваются по длине марки средства передвижения)"
//    ) { _, attempts, _, scanner, _ ->
//        if (CollectionOfVehicles.globalCollection!!.size != 0) {
//            println("Введите марку средства передвижения")
//            val name = tryGet(scanner.getNextLine(), attempts, "Имя не может быть пустой строкой") { takeIf { isNotBlank() } }
//                ?: return@BoundCommand
//            if (CollectionOfVehicles.globalCollection.any { it.compareTo(name) < 0 }) {
//                CollectionOfVehicles.globalCollection.removeIf { elem ->
//                    elem.compareTo(name) < 0
//                }
//                println("Элементы удалены")
//            } else {
//                println("Элементов с более короткой маркой не найдено")
//            }
//        } else {
//            println("Коллекция пуста")
//        }
//    },
//
//    "sum_of_engine_power" to BoundCommand("sum_of_engine_power", "вывести сумму значений мощностей двигателей всех элементов") { _, _, _, _, _ ->
//        if (CollectionOfVehicles.globalCollection!!.size == 0) {
//            println("Коллекция пуста")
//        } else {
//            var summa = 0.0
//            for (elem in CollectionOfVehicles.globalCollection) {
//                summa += elem.enginePower
//            }
//            println(summa)
//        }
//    },
//
//    "group_counting_by_type" to BoundCommand("group_counting_by_type", "сгруппировать элементы коллекции по значению типа средства передвижения, вывести количество элементов в каждой группе"
//    ) { _, _, _, _, _ ->
//        val countOfCar = CollectionOfVehicles.globalCollection.count {
//            it.type == VehicleType.CAR
//        }
//        val countOfSubmarine = CollectionOfVehicles.globalCollection.count {
//            it.type == VehicleType.SUBMARINE
//        }
//        val countOfShip = CollectionOfVehicles.globalCollection.count {
//            it.type == VehicleType.SHIP
//        }
//        println("$countOfCar - количество машин")
//        println("$countOfSubmarine - количество подводных лодок")
//        println("$countOfShip - количество кораблей")
//    },
//
//    "count_by_type" to BoundCommand(
//        "count_by_type", "вывести количество элементов, значение типа которых равно заданному"
//    ) { args, attempts, _, scanner, _ ->
//        if (CollectionOfVehicles.globalCollection!!.size == 0) {
//            println("Коллекция пуста")
//        } else {
//            val changableId = if (args.size < 1){
//                println("Введите номер соответствующего типа средства передвижения из предложенных")
//                println(VehicleType.getTypes())
//                scanner.getNextLine()
//            } else {
//                args[0]
//            }
//            // ?. - функция или поле берётся, если слева не null, в противном случае результат выражения null
//            val type: VehicleType =
//                tryGet(changableId, attempts, "Введите номер соответствующего типа средства передвижения из предложенных") {
//                    toIntOrNull()?.let(VehicleType::getVehicle)
//                } ?: return@BoundCommand
//            val count = CollectionOfVehicles.globalCollection.count {
//                it.type == type
//            }
//            println(count)
//        }
//    }
//)
//private fun forHelp(requestString: Array<String>, attempts: Int, creator: InstanceCreator, parser: AbstractParser, globalArgs: Application) {
//    for ((name, command) in information) {
//        println("$name - ${command.help}")
//    }
//}
