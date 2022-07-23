package com.github.diosa.dms.mainGUI

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.collection.CollectionInMemory
import com.github.diosa.dms.collection.Vehicle
import com.github.diosa.dms.commandHandle.ButtonController
import com.github.diosa.dms.commandHandle.LogInController
import com.github.diosa.dms.commandHandle.SignUpController
import com.github.diosa.dms.commands.GetCollection
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXButton
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import java.io.IOException

class MainSceneController {
    var user: User? = null
    lateinit var nick: Label

    @FXML private lateinit var signUpBtn: JFXButton
    @FXML private lateinit var logInBtn: JFXButton
    @FXML private lateinit var addIfMin: JFXButton
    @FXML private lateinit var clear: JFXButton
    @FXML private lateinit var removeById: JFXButton
    @FXML private lateinit var removeLower: JFXButton
    @FXML private lateinit var update: JFXButton
    @FXML private lateinit var countByType: JFXButton
    @FXML private lateinit var groupCountingByType: JFXButton
    @FXML private lateinit var removeFirst: JFXButton
    @FXML private lateinit var sumOfEnginePower: JFXButton
    @FXML private lateinit var add: JFXButton

    @FXML private lateinit var table: TableView<VehicleTable>

    @FXML private lateinit var id: TableColumn<VehicleTable, Int>
    @FXML private lateinit var name: TableColumn<VehicleTable, String>
    @FXML private lateinit var x: TableColumn<VehicleTable, Float>
    @FXML private lateinit var y: TableColumn<VehicleTable, Int>
    @FXML private lateinit var creationDate: TableColumn<VehicleTable, String>
    @FXML private lateinit var enginePower: TableColumn<VehicleTable, Float>
    @FXML private lateinit var vehicleType: TableColumn<VehicleTable, String>
    @FXML private lateinit var fuelType: TableColumn<VehicleTable, String>
    @FXML private lateinit var username: TableColumn<VehicleTable, String>

    private val data: ObservableList<VehicleTable> = FXCollections.observableArrayList();

    fun collectionVisualise(){
        val answer = RequestManager.manage(null, GetCollection)
        val collection: CollectionInMemory = answer.collection ?: throw NullPointerException()
        println(collection)
        val iter: Iterator<Vehicle> = collection.iterator()

        while (iter.hasNext()){
            val i = iter.next()
            data.add(VehicleTable(SimpleIntegerProperty(i.id!!), SimpleStringProperty(i.name), SimpleFloatProperty(i.coordinates.x),
                SimpleIntegerProperty(i.coordinates.y), SimpleStringProperty(i.creationDate.toString()), SimpleFloatProperty(i.enginePower),
                SimpleStringProperty(i.type.toString()), SimpleStringProperty(i.fuelType.toString()), SimpleStringProperty(i.username)))
        }

        this.id.cellValueFactory = PropertyValueFactory("id")
        this.name.cellValueFactory = PropertyValueFactory("name")
        this.x.cellValueFactory = PropertyValueFactory("x")
        this.y.cellValueFactory = PropertyValueFactory("y")
        this.creationDate.cellValueFactory = PropertyValueFactory("creationDate")
        this.enginePower.cellValueFactory = PropertyValueFactory("enginePower")
        this.vehicleType.cellValueFactory = PropertyValueFactory("vehicleType")
        this.fuelType.cellValueFactory = PropertyValueFactory("fuelType")
        this.username.cellValueFactory = PropertyValueFactory("username")

        table.items = data
    }

    @FXML private fun buttonHandle(buttonName: String, controller: ButtonController): Stage {
        val stage = Stage()
        val loader = FXMLLoader()
        loader.location = MainSceneController::class.java.getResource("/$buttonName.fxml")
        try {
            stage.scene = Scene(loader.load())
        } catch (ex: IOException) {
            println(ex.message)
        }
        stage.title = buttonName
        loader.setController(controller)
        stage.show()
        return stage
    }

    @FXML
    private fun signUpHandle() {
            this.signUpBtn.setOnAction { buttonHandle("SignUp", SignUpController()) }
    }

    @FXML
    private fun logInHandle() {
        this.logInBtn.setOnAction{
            val logInController = LogInController()
            val stage = buttonHandle("LogIn", logInController)
            stage.setOnCloseRequest{
                setComponents()

            }
        }
    }

    private fun setComponents() {
        if (user != null) {
            this.nick.text = user!!.login
            nick.isVisible = true
            this.signUpBtn.isVisible = false
            this.logInBtn.isVisible = false
            this.add.isVisible = true
            this.addIfMin.isVisible = true
            this.clear.isVisible = true
            this.countByType.isVisible = true
            this.groupCountingByType.isVisible = true
            this.removeById.isVisible = true
            this.removeFirst.isVisible = true
            this.removeLower.isVisible = true
            this.sumOfEnginePower.isVisible = true
            this.update.isVisible = true
        }
    }
}