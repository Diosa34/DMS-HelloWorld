package com.github.diosa.dms.mainGUI

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import javafx.beans.property.FloatProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.StringProperty

class VehicleTable(
    val id: IntegerProperty,
    val name: StringProperty,
    val x: FloatProperty,
    val y: IntegerProperty,
    val creationDate: StringProperty,
    val enginePower: FloatProperty,
    val vehicleType: StringProperty,
    val fuelType: StringProperty,
    val username: StringProperty

): RecursiveTreeObject<VehicleTable>() {

}
