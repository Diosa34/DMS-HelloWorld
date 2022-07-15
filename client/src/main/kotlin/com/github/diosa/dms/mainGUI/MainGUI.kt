package com.github.diosa.dms.mainGUI

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.stage.Stage

fun main(){
    MainGUI().main()
}

class MainGUI: Application() {
    fun main(){
        launch()
    }

    override fun start(stage: Stage) {
        val loader = FXMLLoader()
        loader.location = javaClass.getResource("/main.fxml")
//        val gp: GridPane = loader.load<GridPane>()
//
//        stage.scene = Scene(gp)
        stage.title = "Vehicle visualizer"

        loader.setController(MainSceneController())
        stage.show()
    }
}
