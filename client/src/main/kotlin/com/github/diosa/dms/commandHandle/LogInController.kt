package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.commands.LogIn
import com.github.diosa.dms.mainGUI.SuccessEvent
import com.github.diosa.dms.mainGUI.SuccessEvent.Companion.LOGIN_COMPLETED
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import javafx.event.Event
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Label

class LogInController: CommandController {
    @FXML private lateinit var login: JFXTextField
    @FXML private lateinit var password: JFXPasswordField
    @FXML private lateinit var message: Label

    @FXML private lateinit var readyButton: JFXButton

    fun addReadyButtonHandler(handler: EventHandler<SuccessEvent>){
        this.readyButton.addEventHandler(SuccessEvent.LOGIN_COMPLETED, handler)
        println("Кнопке добавился handler")
    }

    @FXML
    private fun loginButtonHandle() {
//        this.readyButton.setOnAction {
            val login: String = this.login.text
            val password: String = this.password.text

            if (login.isNotBlank() && password.isNotBlank()) {
                val answer = RequestManager.manage(null, LogIn(login, password))
                this.message.text = answer.result
                if (answer.success) {
                    val event: Event = SuccessEvent(answer.user)
                    this.readyButton.fireEvent(event)
                    println("Событие успешной авторизации запущено")
                }
            } else {
                this.message.text = "Поля не должны быть пустыми"
                this.login.text = ""
                this.password.text = ""
            }
//        }
    }
}