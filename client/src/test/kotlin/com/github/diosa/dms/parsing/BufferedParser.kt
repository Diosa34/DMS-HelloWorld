//package com.github.diosa.dms.parsing
//
//import com.github.diosa.dms.AbstractParser
//
///**
// * Reads commands from a preset array
// */
//class BufferedParser(
//    vararg rows: String
//): AbstractParser(){
//    private val rowsIterator: Iterator<String> = rows.iterator()
//
//    override fun hasNextLine(): Boolean {
//        return this.rowsIterator.hasNext()
//    }
//
//    override fun getNextLine(): String {
//        return this.rowsIterator.next()
//    }
//}