package com.github.Diosa34.DMS_HelloWorld.manage

import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.server.Server
import com.github.Diosa34.DMS_HelloWorld.sql.SQLUsersCollection
import java.net.BindException
import java.net.ConnectException
import java.net.InetAddress
import java.net.SocketException
import java.util.logging.Level
import java.util.logging.Logger

object ServerManager {
    fun manager(host: InetAddress, port: Int, collection: CollectionOfVehicles, usersCollectionWrap: SQLUsersCollection, log: Logger) {
        try {
            while (true) {
                val server = Server(host, port, log)
                try {
                    while (true) {
                        server.receive(collection, usersCollectionWrap)
                    }
                } catch (ex: ConnectException) {
                    log.info("Завершена работа клиентского приложения")
                    log.log(Level.INFO, "ConnectException: ", ex)
                } catch (ex: SocketException) {
                    log.log(Level.SEVERE, "SocketException: ", ex)
                }
            }
        } catch (ex: BindException){
            log.log(Level.SEVERE, "BindException: ", ex)
        }
    }
}