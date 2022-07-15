package com.github.diosa.dms.mainGUI

import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType

object Alert{
    private val notification = javafx.scene.control.Alert(AlertType.NONE)
    private val error = javafx.scene.control.Alert(AlertType.ERROR)

    fun notification(message: String){
        notification.close();
        notification.dialogPane.buttonTypes.add(ButtonType.OK);
        notification.contentText = message;
        notification.show()
    }

    @JvmStatic
    fun error(message: String){
        error.close();
        error.dialogPane.buttonTypes.add(ButtonType.OK);
        error.contentText = message;
        error.show()
    }
}