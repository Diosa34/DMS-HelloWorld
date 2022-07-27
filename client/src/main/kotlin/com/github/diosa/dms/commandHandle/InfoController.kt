package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.commands.Info
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML
import javafx.stage.Stage

class InfoController(
    val user: User
): CommandController {
    @FXML private lateinit var typeOfCollection: JFXTextField
    @FXML private lateinit var initDate: JFXTextField
    @FXML private lateinit var elemCount: JFXTextField

    @FXML private lateinit var apply: JFXButton

    fun infoButtonHandle(){
        val answer = RequestManager.manage(this.user, Info).result.split(";")
        this.typeOfCollection.text = answer[0]
        this.initDate.text = answer[1]
        this.elemCount.text = answer[2]
    }

    @FXML
    private fun applyButtonHandle(){
        val stage: Stage = this.apply.scene.window as Stage
        stage.close()
    }
}