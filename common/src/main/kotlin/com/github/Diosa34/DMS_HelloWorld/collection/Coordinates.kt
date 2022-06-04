package com.github.Diosa34.DMS_HelloWorld.collection

import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.Serializable

/**
 * Entity coordinate class
 */
@Serializable
class Coordinates(
    val x: Float, //Поле не может быть null
    val y: Int    //Поле не может быть null
)