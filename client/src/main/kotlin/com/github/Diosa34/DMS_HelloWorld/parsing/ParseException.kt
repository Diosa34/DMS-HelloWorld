package com.github.Diosa34.DMS_HelloWorld.parsing

import java.lang.Exception

class ParseException : Exception(){
    companion object {
        const val message: String = "Аргументы для команды не были получены, введите команду заново."
    }
}