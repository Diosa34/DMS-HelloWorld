package com.github.Diosa34.DMS_HelloWorld.parsing

import java.io.InputStream
import java.util.*

/**
 * Читает команды из класса Scanner
 */
class ScannerParser(input: InputStream) : AbstractParser() {
    private val scanner = Scanner(input)

    override fun hasNextLine(): Boolean {
        return this.scanner.hasNextLine()
    }

    override fun getNextLine(): String {
        return this.scanner.nextLine()
    }


}