package com.github.diosa.dms.exceptions

import java.lang.Exception

class UnexpectedCommandException : Exception() {
    companion object {
        const val message: String = "Введена несуществующая команда, информация о командах доступна по команде 'help'"
    }
}