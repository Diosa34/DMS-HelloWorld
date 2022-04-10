package com.github.Diosa34.DMS_HelloWorld.parsing

/**
 * Читает команды из предустановленного массива
 */
class BufferedParser(
    vararg rows: String
): AbstractParser(){
    private val rowsIterator: Iterator<String> = rows.iterator()

    override fun hasNextLine(): Boolean {
        return this.rowsIterator.hasNext()
    }

    override fun getNextLine(): String {
        return this.rowsIterator.next()
    }
}