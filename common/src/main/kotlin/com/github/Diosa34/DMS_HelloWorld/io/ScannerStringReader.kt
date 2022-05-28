package com.github.Diosa34.DMS_HelloWorld.io

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractStringReader
import java.io.InputStream
import java.util.*

/**
 * Reads commands using class Scanner
 */
open class ScannerStringReader(input: InputStream) : AbstractStringReader() {
    private val scanner = Scanner(input)

    override fun hasNextLine(): Boolean {
        return this.scanner.hasNextLine()
    }

    override fun getNextLine(): String {
        return this.scanner.nextLine()
    }
}