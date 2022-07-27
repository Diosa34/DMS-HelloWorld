package com.github.diosa.dms.client

import javafx.scene.control.Label

/**
 * Verification the validity of an argument
 */
fun <T: Any> tryGet(field: String, message: String, label: Label, number: String.() -> T?) : T? {
    field.number().let { number ->
        if (number != null) {
            return@tryGet number
        }
        setMessage(message, label)
        return null
    }
}

private fun setMessage(message: String, lable: Label){
    lable.text = message
    lable.isVisible = true
}