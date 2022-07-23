package com.github.diosa.dms.mainGUI

import javafx.application.Application

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        Application.launch(StartGUI::class.java, *args)
    }
}