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

class SignUpController: ButtonController {
    @FXML
    private lateinit var login: JFXTextField
    @FXML
    private lateinit var password: JFXPasswordField
    @FXML
    private lateinit var passwordAgain: JFXPasswordField
    @FXML
    private lateinit var readyButton: JFXButton
    @FXML
    private fun signUpButtonHandle(){
        this.readyButton.setOnAction{
            val login: String = tryGet(this.login.text) { takeIf { isNotBlank() } } ?: throw NotValidFieldException("Поле 'login' не должно быть пустым")
            val password: String = tryGet(this.password.text) { takeIf { isNotBlank() } } ?: throw NotValidFieldException("Поле 'password' не должно быть пустым")
            val passwordAgain: String = tryGet(this.passwordAgain.text) { takeIf { isNotBlank() } } ?: throw NotValidFieldException("Поле 'passwordAgain' не должно быть пустым")
            if (password == passwordAgain){
                RequestManager.manage(null, SignUp(login, password))
            } else{
                throw MismatchedPasswordsException("Пароли не совпадают!")
            }
        }
    }
}