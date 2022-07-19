package com.github.diosa.dms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainSceneController mainController = new MainSceneController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartGUI.class.getResource("/main.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Vehicle visualizer");

        loader.setController(mainController);
        stage.show();
    }
}
