package com.github.diosa.dms.client

import com.github.diosa.dms.absctactions.BoundCommand
import com.github.diosa.dms.commands.LogIn
import com.github.diosa.dms.commands.SignUp
import com.github.diosa.dms.serialize.*
import com.github.diosa.dms.users.User
import io.github.landgrafhomyak.itmo.dms_lab.io.AsByteArrayDecoder
import io.github.landgrafhomyak.itmo.dms_lab.io.AsByteArrayEncoder
import java.net.ConnectException
import java.net.SocketException

class RequestManager {
    companion object{
        @OptIn(ExperimentalUnsignedTypes::class)
        fun manage(user: User?, command: BoundCommand): OneLineAnswer {
            when (command) {
                is ExecuteScript -> if (user != null) {command.execute()}
            }
            try {
                var request = Request(command)
                if (command !is SignUp && command !is LogIn && user != null){
                    request = Request(command, user)
                }
                val encoder = AsByteArrayEncoder()
                Request.serializer().serialize(encoder, request)
                Client.send(encoder.export().toByteArray())
                Client.receive()
            } catch (ex: ConnectException) {
                return OneLineAnswer(user, "Соединение прервано, перезапустите сервер, затем клиента")
            } catch (ex: SocketException) {
                return OneLineAnswer(user, "Соединение прервано, перезапустите сервер, затем клиента, ошибка сокета")
            }
            return OneLineAnswer.serializer().deserialize(AsByteArrayDecoder(Client.getArr().toUByteArray()))
        }
    }
}