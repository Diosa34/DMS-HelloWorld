package com.github.diosa.dms.client

import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.*
import java.util.logging.Level
import java.util.logging.LogManager
import kotlin.system.exitProcess

object Client {
    private val log: java.util.logging.Logger
    private lateinit var host: InetAddress
    private const val port: Int = 5489
    lateinit var sock: Socket
    private val arr: ByteArray = ByteArray(1024 * 1024)

    init {
        this.log = getLogger()
        this.init()
    }

    private fun init() {
        try {
            this.host = InetAddress.getLocalHost()
            this.sock = Socket(this.host, this.port)
            return
        } catch (ex: ConnectException) {
            log.log(Level.SEVERE, "ConnectException: ", ex)
            println("Соединение не установлено, запустите сервер, а затем клиент.")
        } catch (e: UnknownHostException) {
            log.log(Level.SEVERE, "UnknownHostException", e)
            println("Неизвестный хост")
        } catch (e: SocketException) {
            println("Ошибка создания сокета")
        }
        exitProcess(1)
    }

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

    private fun getLogger(): java.util.logging.Logger {
        val log: java.util.logging.Logger = java.util.logging.Logger.getLogger("ClientLogger")
        try {
            LogManager.getLogManager().readConfiguration(
//            FileInputStream("./clientLog.properties")
                FileInputStream(
                    "C:\\Users\\Diosa\\IdeaProjects\\Laboratory5\\client\\src\\main\\resources" +
                            "\\clientLog.properties"
                )
            )
            log.info("Начало работы клиентского приложения")

        } catch (e: IOException) {
            println("Could not setup logger configuration: $e")
        } catch (ex: ClassNotFoundException) {
            println("Logger not configured")
        }
        return log
    }
}