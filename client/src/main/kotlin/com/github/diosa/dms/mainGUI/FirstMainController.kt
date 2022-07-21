package com.github.diosa.dms

import com.github.diosa.dms.commandHandle.ButtonController
import com.github.diosa.dms.commandHandle.LogInController
import com.github.diosa.dms.commandHandle.SignUpController
import com.github.diosa.dms.exceptions.MismatchedPasswordsException
import com.github.diosa.dms.exceptions.NotValidFieldException
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXButton
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage
import java.io.IOException

class MainSceneController {
    private var user: User? = null

    @FXML
    private lateinit var nick: Label

    @FXML
    private lateinit var signUpBtn: JFXButton

    @FXML
    private lateinit var logInBtn: JFXButton

    @FXML
    private lateinit var addIfMin: JFXButton

    @FXML
    private lateinit var clear: JFXButton

    @FXML
    private lateinit var removeById: JFXButton

    @FXML
    private lateinit var removeLower: JFXButton

    @FXML
    private lateinit var update: JFXButton

    @FXML
    private lateinit var countByType: JFXButton

    @FXML
    private lateinit var groupCountingByType: JFXButton

    @FXML
    private lateinit var removeFirst: JFXButton

    @FXML
    private lateinit var sumOfEnginePower: JFXButton

    @FXML
    private lateinit var add: JFXButton

    @FXML
    private fun buttonHandle(buttonName: String, controller: ButtonController): Stage {
        val stage = Stage()
        val loader = FXMLLoader()
        loader.location = MainSceneController::class.java.getResource("/$buttonName.fxml")
        try {
            stage.scene = Scene(loader.load())
        } catch (ex: IOException) {
            println(ex.message)
        }
        stage.title = buttonName
        loader.setController(controller)
        stage.show()
        return stage
    }

    @FXML
    private fun signUpHandle() {
        try {
            this.signUpBtn.setOnAction { buttonHandle("SignUp", SignUpController()) }
        } catch (ex: NotValidFieldException){
            com.github.diosa.dms.mainGUI.Alert.warning(ex.message)
        } catch (ex: MismatchedPasswordsException) {
            com.github.diosa.dms.mainGUI.Alert.warning(ex.message)
        }
    }

    @FXML
    private fun logInHandle() {
        try {
            this.logInBtn.setOnAction{
                val logInController = LogInController()
                val stage = buttonHandle("LogIn", logInController)
                stage.setOnCloseRequest{
                    this.user = logInController.user
                    setComponents()
                    this.signUpBtn.isVisible = false
                    this.logInBtn.isVisible = false
                    this.add.isVisible = true
                    this.addIfMin.isVisible = true
                    this.clear.isVisible = true
                    this.countByType.isVisible = true
                    this.groupCountingByType.isVisible = true
                    this.removeById.isVisible = true
                    this.removeFirst.isVisible = true
                    this.removeLower.isVisible = true
                    this.sumOfEnginePower.isVisible = true
                    this.update.isVisible = true
                }
            }
        } catch (ex: NotValidFieldException) {
            com.github.diosa.dms.mainGUI.Alert.warning(ex.message)
        }
    }

    private fun setComponents() {
        if (user != null) {
            this.nick.text = user!!.login
            nick.isVisible = true
            //перенести сюда изменение видимости кнопок
        }
    }
}