package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.commands.GroupCountingByType
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML
import javafx.stage.Stage

class GroupCountingByTypeController(
    val user: User
): CommandController {

    @FXML private lateinit var car: JFXTextField
    @FXML private lateinit var ship: JFXTextField
    @FXML private lateinit var submarine: JFXTextField

    @FXML private lateinit var apply: JFXButton

    fun groupCountingButtonHandle(){
        val answer = RequestManager.manage(this.user, GroupCountingByType).result.split(";")
        this.car.text = answer[0]
        this.ship.text = answer[1]
        this.submarine.text = answer[2]
    }

    @FXML
    private fun applyButtonHandle(){
        val stage: Stage = this.apply.scene.window as Stage
        stage.close()
    }
}