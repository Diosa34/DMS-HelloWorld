package com.github.diosa.dms.client

import com.github.diosa.dms.absctactions.BoundCommand
import com.github.diosa.dms.commands.Exit
import com.github.diosa.dms.commands.LogIn
import com.github.diosa.dms.exceptions.NotAuthorizedException
import com.github.diosa.dms.exceptions.ParseException
import com.github.diosa.dms.exceptions.UnexpectedCommandException
import com.github.diosa.dms.mainGUI.Alert
import com.github.diosa.dms.serialize.*
import com.github.diosa.dms.users.User
import io.github.landgrafhomyak.itmo.dms_lab.io.AsByteArrayDecoder
import io.github.landgrafhomyak.itmo.dms_lab.io.AsByteArrayEncoder
import java.net.ConnectException
import java.net.SocketException
import java.rmi.registry.Registry

class RequestManager {
    companion object{
        @OptIn(ExperimentalUnsignedTypes::class)
        fun manage(user: User?, command: BoundCommand): User? {
            if (command !is Registry && command !is LogIn && user == null) {
                throw NotAuthorizedException("Перед началом работы необходимо авторизоваться")
            }
            when (command) {
                is ExecuteScript -> if (user != null) {command.execute()}
                is Exit -> {
                    Client.sock.close()
                    return null
                }
            }
            try {
                var request = Request(command)
                if (command !is Registry && command !is LogIn && user != null){
                    request = Request(command, user)
                }
                val encoder = AsByteArrayEncoder()
                Request.serializer().serialize(encoder, request)
                Client.send(encoder.export().toByteArray())
                Client.receive()
            } catch (ex: ConnectException) {
                Alert.error("Соединение прервано, перезапустите сервер, затем клиента")
                return null
            } catch (ex: SocketException) {
                Alert.error("Соединение прервано, перезапустите сервер, затем клиента, ошибка сокета")
                return null
            }
            val answer = OneLineAnswer.serializer().deserialize(AsByteArrayDecoder(Client.getArr().toUByteArray()))
            Alert.notification(answer.result)
            return answer.user
        }
    }
}