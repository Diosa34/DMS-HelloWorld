package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.commands.SumOfEnginePower
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML

class SumOfEnginePowerController(
    val user: User
): CommandController {
    @FXML private lateinit var summ: JFXTextField

    @FXML private fun sumOfEnginePowerHandle() {
        this.summ.text = RequestManager.manage(this.user, SumOfEnginePower).result
    }
}