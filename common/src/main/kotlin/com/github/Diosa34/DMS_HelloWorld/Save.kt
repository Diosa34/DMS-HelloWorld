package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.commands.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractParser
import com.github.Diosa34.DMS_HelloWorld.parsing.AbstractStringReader
import com.github.Diosa34.DMS_HelloWorld.parsing.FileVerification
import com.github.Diosa34.ObjectConverter.Converter

class Save(
    private val stringReader: AbstractStringReader,
    private val globalArgs: Application
): ApplicableToCollection {
    override fun execute(collection: CollectionOfVehicles) {
        var filename = globalArgs.filepath
        if (!FileVerification.fullVerification(filename)) {
            println("Введите путь к файлу, в который хотите записать коллекцию")
            filename = stringReader.getNextLine()
            if (!FileVerification.fullVerification(filename)) {
                return@execute
            } else {
                globalArgs.filepath = filename
            }
        }
        val converter = Converter(filename)
        converter.xmlInitialization(CollectionOfVehicles.globalCollection, 0)
        println("Коллекция успешно сохранена")
    }

    companion object{
        const val title: String = "save"
        const val help: String = "сохранить коллекцию в файл"
    }
}