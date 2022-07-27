package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.client.tryGet
import com.github.diosa.dms.commands.RemoveById
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML
import javafx.scene.control.Label

class RemoveByIdController(
    val user: User
): CommandController {
    @FXML private lateinit var id: JFXTextField
    @FXML private lateinit var label: Label
    @FXML private lateinit var readyButton: JFXButton

    @FXML
    private fun removeByIdButtonHandle() {
        val newId: Int? = tryGet(this.id.text, "ID должно быть целым числом", this.label){toIntOrNull()}
        if (newId != null){
            this.label.text = RequestManager.manage(this.user, RemoveById(newId)).result
        }
    }
}