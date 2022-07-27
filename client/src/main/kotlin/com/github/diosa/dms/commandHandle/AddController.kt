package com.github.diosa.dms.commandHandle

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.client.tryGet
import com.github.diosa.dms.collection.Coordinates
import com.github.diosa.dms.collection.FuelType
import com.github.diosa.dms.collection.Vehicle
import com.github.diosa.dms.collection.VehicleType
import com.github.diosa.dms.commands.Add
import com.github.diosa.dms.commands.AddIfMin
import com.github.diosa.dms.commands.Update
import com.github.diosa.dms.exceptions.UnexpectedCommandException
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXComboBox
import com.jfoenix.controls.JFXTextField
import javafx.fxml.FXML
import javafx.scene.control.Label
import kotlinx.datetime.Clock

class AddController(
    val user: User
): CommandController {
    @FXML lateinit var id: JFXTextField
    @FXML lateinit var idLabel: Label
    @FXML private lateinit var name: JFXTextField
    @FXML private lateinit var nameLabel: Label
    @FXML private lateinit var x: JFXTextField
    @FXML private lateinit var xLabel: Label
    @FXML private lateinit var y: JFXTextField
    @FXML private lateinit var yLabel: Label
    @FXML private lateinit var vehicleType: JFXComboBox<VehicleType>
    @FXML private lateinit var fuelType: JFXComboBox<FuelType>
    @FXML private lateinit var enginePower: JFXTextField
    @FXML private lateinit var epLabel: Label

    @FXML private lateinit var label: Label

    @FXML lateinit var addButton: JFXButton
    @FXML lateinit var addIfMinButton: JFXButton
    @FXML lateinit var updateButton: JFXButton

    @FXML
    private fun addButtonHandle() {
        this.addButton.setOnAction {
            validate(addButton.text)
        }
    }

    @FXML
    private fun addIfMinButtonHandle() {
        this.addIfMinButton.setOnAction {
            validate(addIfMinButton.text)
        }
    }

    @FXML
    private fun updateButtonHandle() {
        this.updateButton.setOnAction {
            validate(updateButton.text)
        }
    }

    private fun validate(buttonText: String) {
        val id: Int? = tryGet(this.id.text, "Поле 'id' должно быть целым числом", idLabel){toIntOrNull()}
        val newName: String? = tryGet(this.name.text, "Поле не должно быть пустым", nameLabel){takeIf { isNotBlank() }}
        val newX: Float? = tryGet(this.x.text, "Поле 'x' должно быть вещественным числом", xLabel){toFloatOrNull()}
        val newY: Int? = tryGet(this.y.text, "Поле 'y' должно быть целым числом", yLabel){toIntOrNull()}
        val newEnginePower: Float? = tryGet(this.enginePower.text, "Поле 'enginePower' должно быть вещественным числом", epLabel){toFloatOrNull()}
        if (newName != null && newX != null && newY != null && newEnginePower != null) {
            val answer = when (buttonText) {
                "Add" -> RequestManager.manage(user, Add(Vehicle(null, newName, Coordinates(newX, newY), Clock.System.now(),
                         newEnginePower, this.vehicleType.value, this.fuelType.value, user.login)))
                "Add if min" -> RequestManager.manage(user, AddIfMin(newName, Vehicle(null, newName, Coordinates(newX, newY), Clock.System.now(),
                                newEnginePower, this.vehicleType.value, this.fuelType.value, user.login)))
                "Update" -> {
                    if (id != null) {
                        RequestManager.manage(user, Update(id, Vehicle(null, newName, Coordinates(newX, newY), Clock.System.now(),
                            newEnginePower, this.vehicleType.value, this.fuelType.value, user.login)))
                    } else {
                        RequestManager.manage(user, Update(-1, Vehicle(null, newName, Coordinates(newX, newY), Clock.System.now(),
                            newEnginePower, this.vehicleType.value, this.fuelType.value, user.login)))
                    }
                }
                else -> throw UnexpectedCommandException()
            }
            this.label.text = answer.result
//                if (answer.success) {
//                }
        }
    }
}