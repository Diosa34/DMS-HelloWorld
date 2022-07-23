package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.commands.LogIn
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML
import javafx.scene.control.Label

class LogInController(
//    private val mainScene: MainSceneController
): ButtonController{
    @FXML
    private lateinit var login: JFXTextField
    @FXML
    private lateinit var password: JFXPasswordField
    @FXML
    private lateinit var message: Label
    @FXML
    private lateinit var readyButton: JFXButton
    @FXML
    private fun loginButtonHandle(){
//        this.readyButton.addEventHandler(ActionEvent.ACTION, ParentNode())
        this.readyButton.setOnAction{
            val login: String = this.login.text
            val password: String = this.password.text

            if (login.isNotBlank() && password.isNotBlank()) {
                val answer = RequestManager.manage(null, LogIn(login, password))
                this.message.text = answer.result
                this.message.isVisible = true
//                if (answer.success){
//                    this.mainScene.user = answer.user
//                    this.mainScene.nick.text = login
//                }
            } else {
                this.message.text = "Поля не должны быть пустыми"
                this.login.text = ""
                this.password.text = ""
            }
        }
    }
}