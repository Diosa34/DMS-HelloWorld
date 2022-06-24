package com.github.diosa.dms

import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.Socket

class Client(
    private val host: InetAddress,
    private val port: Int,
    private val log: java.util.logging.Logger
) {
    val sock: Socket = Socket(this.host ,this.port)
    private val arr: ByteArray = ByteArray(1024 * 1024)

    fun send(serializedCommand: ByteArray) {
        val os: OutputStream = sock.getOutputStream()
        os.write(serializedCommand)
        this.log.info("Отправлен новый запрос на сервер")
    }

    fun receive() {
        val inputStream: InputStream = sock.getInputStream()
        inputStream.read(arr)
        this.log.info("Получен ответ от сервера")
    }

    fun getArr(): ByteArray {
        return this.arr
    }
}