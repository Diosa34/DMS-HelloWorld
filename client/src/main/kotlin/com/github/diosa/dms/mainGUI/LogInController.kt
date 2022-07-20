package com.github.diosa.dms.mainGUI

import com.github.diosa.dms.client.NotValidFieldException
import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.client.tryGet
import com.github.diosa.dms.commands.LogIn
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML
import javafx.scene.control.Button

class LogInController{
    var user: User? = null
    @FXML
    private lateinit var login: JFXTextField
    @FXML
    private lateinit var password: JFXPasswordField
    @FXML
    private lateinit var readyButton: JFXButton
    @FXML
    private fun loginButtonHandle(){
        try {
            this.readyButton.setOnAction{
                val login: String = tryGet(login.text) { takeIf { isNotBlank() } } ?: throw NotValidFieldException("Поле 'login' не должно быть пустым")
                val password: String = tryGet(password.text) { takeIf { isNotBlank() } } ?: throw NotValidFieldException("Поле 'password' не должно быть пустым")
                this.user = RequestManager.manage(user, LogIn(login, password))
            }
        } catch (ex: NotValidFieldException) {
            Alert.warning(ex.message)
        }

    }
}