package com.github.diosa.dms;

import com.github.diosa.dms.mainGUI.LogInController;
import com.github.diosa.dms.mainGUI.SignUpController;
import com.github.diosa.dms.users.User;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSceneController {
    private User user;
    @FXML
    private JFXButton signUpBtn;
    @FXML
    private JFXButton logInBtn;
    @FXML
    private void signUpHandle() {
        this.signUpBtn.setOnAction( e -> {
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
            try{
                stage.setScene(new Scene(loader.load()));
            } catch (IOException ex){
                System.out.println(ex.getMessage());
            }
            stage.setTitle("Authorization");

            LogInController logInController = new LogInController();
            loader.setController(logInController);
            stage.show();
            this.user = logInController.getUser();
        });
    }
}
