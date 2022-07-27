package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.client.tryGet
import com.github.diosa.dms.commands.RemoveLower
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML
import javafx.scene.control.Label

class RemoveLowerController(
    val user: User
): CommandController {
    @FXML private lateinit var name: JFXTextField
    @FXML private lateinit var label: Label

    @FXML private fun removeLowerButtonHandle() {
        val newName: String? = tryGet(this.name.text, "Поле 'Name' не должно быть пустым", this.label){takeIf { isNotBlank() }}
        if (newName != null){
            this.label.text = RequestManager.manage(this.user, RemoveLower(newName)).result
        }
    }
}