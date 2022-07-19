//package com.github.diosa.dms.mainGUI
//
//import com.github.diosa.dms.users.User
//import javafx.fxml.FXML
//import javafx.fxml.FXMLLoader
//import javafx.scene.control.Button
//import javafx.stage.Stage
//
//public class MainSceneController {
//    private var user: User? = null
//    @FXML
//    private val signUpBtn: Button? = null
//    @FXML
//    private var logInBtn: Button? = null
//    @FXML
//    private fun signUpHandle() {
//        this.signUpBtn.setOnAction {
//            val stage = Stage()
//            val loader = FXMLLoader()
//            loader.location = javaClass.getResource("/SignUp.fxml")
//            loader.setController(SignUpController())
//            stage.show()
//        }
//    }
//    @FXML
//    private fun logInHandle() {
//        this.logInBtn?.setOnAction {
//            val stage = Stage()
//            val loader = FXMLLoader()
//            loader.location = javaClass.getResource("/LogIn.fxml")
//
//            val logInController = LogInController(this.user)
//            loader.setController(logInController)
//            this.user = logInController.user
//            stage.show()
//        }
//    }
//}