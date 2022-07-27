package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.commands.Help
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTextArea
import javafx.fxml.FXML
import javafx.stage.Stage

class HelpController(
    val user: User
): CommandController {
    @FXML private lateinit var area: JFXTextArea
    @FXML private lateinit var applyButton: JFXButton

    fun helpHandle(){
        this.area.text = RequestManager.manage(this.user, Help).result
    }

    @FXML
    private fun applyHandle() {
        val stage: Stage = this.applyButton.scene.window as Stage
        stage.close()
    }

}