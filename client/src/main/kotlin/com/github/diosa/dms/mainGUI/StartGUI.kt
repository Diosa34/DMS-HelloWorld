package com.github.diosa.dms.mainGUI

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.IOException

class StartGUI : Application() {
    @Throws(IOException::class)
    override fun start(stage: Stage) {
        val loader = FXMLLoader()
        loader.location = StartGUI::class.java.getResource("/main.fxml")
        val root = loader.load<Parent>()
        stage.scene = Scene(root)
        stage.title = "Vehicle visualizer"
        loader.setController(MainSceneController())
//        mainController.collectionVisualise()
        stage.show()
    }
}