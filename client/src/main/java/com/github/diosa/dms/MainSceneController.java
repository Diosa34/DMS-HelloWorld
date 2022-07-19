package com.github.diosa.dms;

import com.github.diosa.dms.mainGUI.LogInController;
import com.github.diosa.dms.mainGUI.SignUpController;
import com.github.diosa.dms.users.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainSceneController {
    private User user;
    @FXML
    private Button signUpBtn;
    @FXML
    private Button logInBtn;
    @FXML
    private void signUpHandle() {
        this.signUpBtn.setOnAction( a -> {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainSceneController.class.getResource("/SignUp.fxml"));
            loader.setController(new SignUpController());
            stage.show();
        });
    }
    @FXML
    private void logInHandle() {
        this.logInBtn.setOnAction( e -> {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainSceneController.class.getResource("/LogIn.fxml"));

            LogInController logInController = new LogInController(this.user);
            loader.setController(logInController);
            this.user = logInController.getUser();
            stage.show();
        });
    }
}
