package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.ObjectConverter.Converter
//
//class Save(
//    private val stringReader: AbstractStringReader,
//): ApplicableToCollection {
//    override fun execute(collection: CollectionOfVehicles) {
////        var filename = Application.filepath
////        if (!FileVerification.fullVerification(filename)) {
////            println("Введите путь к файлу, в который хотите записать коллекцию")
////            filename = stringReader.getNextLine()
////            filename.also { FileVerification.fullVerification(it) }
////            if (!FileVerification.fullVerification(filename)) {
////                return@execute
////            } else {
////                globalArgs.filepath = filename
////            }
//        }
//        val converter = Converter(filename)
//        converter.xmlInitialization(CollectionOfVehicles.globalCollection, 0)
//        println("Коллекция успешно сохранена")
//    }
//
//    companion object{
//        const val title: String = "save"
//        const val help: String = "сохранить коллекцию в файл"
//    }
//
//    init {
//        TODO()
//    }
//}