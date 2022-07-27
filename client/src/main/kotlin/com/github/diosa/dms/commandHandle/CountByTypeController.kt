package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.collection.VehicleType
import com.github.diosa.dms.commands.CountByType
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML
import javafx.scene.control.ComboBox

class CountByTypeController(
    val user: User
): CommandController {
    @FXML private lateinit var type: ComboBox<VehicleType>
    @FXML private lateinit var count: JFXTextField

    @FXML
    private fun countByTypeHandler() {
        val answer = RequestManager.manage(this.user, CountByType(type.value))
        this.count.text = answer.result
    }
}