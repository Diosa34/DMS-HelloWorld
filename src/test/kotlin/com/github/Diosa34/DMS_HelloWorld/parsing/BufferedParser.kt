package com.github.Diosa34.DMS_HelloWorld.parsing

class BufferedParser(
    vararg rows: String
): AbstractParser(){
    private val iterator: Iterator<String> = rows.iterator()

    override fun hasNextLine(): Boolean {
        return this.iterator.hasNext()
    }

    override fun getNextLine(): String {
        return this.iterator.next()
    }
}