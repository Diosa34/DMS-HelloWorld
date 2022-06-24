package com.github.diosa.dms.collection

import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.Serializable

/**
 * Entity coordinate class
 */
@Serializable
class Coordinates (
    @DisplayName("Координата х")
    val x: Float, //Поле не может быть null
    @DisplayName("Координата у")
    val y: Int    //Поле не может быть null
)