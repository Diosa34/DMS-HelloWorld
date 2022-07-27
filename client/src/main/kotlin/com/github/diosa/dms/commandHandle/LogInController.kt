package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.commands.LogIn
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML
import javafx.scene.control.Label

class LogInController : CommandController {
    @FXML private lateinit var login: JFXTextField
    @FXML private lateinit var password: JFXPasswordField
    @FXML private lateinit var message: Label

    @FXML private lateinit var readyButton: JFXButton

    @FXML
    private fun loginButtonHandle() {
        this.readyButton.setOnAction {
            val login: String = this.login.text
            val password: String = this.password.text

            if (login.isNotBlank() && password.isNotBlank()) {
                val answer = RequestManager.manage(null, LogIn(login, password))
                this.message.text = answer.result
                if (answer.success) {
                }
            } else {
                this.message.text = "Поля не должны быть пустыми"
                this.login.text = ""
                this.password.text = ""
            }
        }
    }
}