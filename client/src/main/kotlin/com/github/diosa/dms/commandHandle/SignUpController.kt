package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.exceptions.NotValidFieldException
import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.client.tryGet
import com.github.diosa.dms.commands.SignUp
import com.github.diosa.dms.exceptions.MismatchedPasswordsException
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML
import javafx.scene.control.Label

class SignUpController : ButtonController {
    @FXML
    private lateinit var login: JFXTextField

    @FXML
    private lateinit var password: JFXPasswordField

    @FXML
    private lateinit var passwordAgain: JFXPasswordField

    @FXML
    private lateinit var readyButton: JFXButton

    @FXML
    private lateinit var message: Label

    private var success: Boolean = false

    @FXML
    private fun signUpButtonHandle() {
        this.readyButton.setOnAction {
            val login: String = this.login.text
            val password: String = this.password.text
            val passwordAgain: String = this.passwordAgain.text
            if (login.isBlank() || password.isBlank()) {
                message.text = "Поля не должны быть пустыми"
                this.login.text = ""
                this.password.text = ""
            } else if (password == passwordAgain) {
                val answer = RequestManager.manage(null, SignUp(login, password))
                message.text = answer.result
                this.success = answer.success
            } else {
                message.text = "Пароли не совпадают!"
                this.login.text = ""
                this.password.text = ""
            }
        }
    }
}