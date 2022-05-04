package com.github.Diosa34.DMS_HelloWorld.parsing

import java.lang.Exception

class UnexpectedCommandException : Exception() {
    companion object {
        const val message: String = "Введена несуществующая команда, информация о командах доступна по команде 'help'"
    }
}