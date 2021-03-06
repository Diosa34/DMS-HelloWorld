package com.github.diosa.dms.mainGUI

import com.github.diosa.dms.client.RequestManager
import com.github.diosa.dms.collection.CollectionInMemory
import com.github.diosa.dms.collection.Vehicle
import com.github.diosa.dms.commandHandle.*
import com.github.diosa.dms.commands.Clear
import com.github.diosa.dms.commands.GetCollection
import com.github.diosa.dms.commands.RemoveFirst
import com.github.diosa.dms.users.User
import com.jfoenix.controls.JFXButton
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class MainSceneController{
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
    @FXML private lateinit var info: JFXButton
    @FXML private lateinit var help: JFXButton

    private lateinit var collection: CollectionInMemory

    private var data: ObservableList<VehicleTable> = FXCollections.observableArrayList();

    @FXML private lateinit var table: TableView<VehicleTable>

    @FXML private lateinit var idColumn : TableColumn<VehicleTable, Int>
    @FXML private lateinit var nameColumn : TableColumn<VehicleTable, String>
    @FXML private lateinit var xColumn : TableColumn<VehicleTable, Float>
    @FXML private lateinit var yColumn : TableColumn<VehicleTable, Int>
    @FXML private lateinit var creationDateColumn : TableColumn<VehicleTable, String>
    @FXML private lateinit var enginePowerColumn : TableColumn<VehicleTable, Float>
    @FXML private lateinit var vehicleTypeColumn : TableColumn<VehicleTable, String>
    @FXML private lateinit var fuelTypeColumn : TableColumn<VehicleTable, String>
    @FXML private lateinit var usernameColumn : TableColumn<VehicleTable, String>

    inner class LoginSuccessHandler: EventHandler<SuccessEvent> {
        override fun handle(event: SuccessEvent) {
            this@MainSceneController.user = event.user!!
            println(this@MainSceneController.user)
        }
    }

    fun collectionInitialize(){
        val answer = RequestManager.manage(null, GetCollection)
        this.collection = answer.collection!!
        
        this.tableSynchronize()
        
        this.idColumn.cellValueFactory = PropertyValueFactory<VehicleTable, Int>("id")
        this.nameColumn.cellValueFactory = PropertyValueFactory("name")
        this.xColumn.cellValueFactory = PropertyValueFactory("x")
        this.yColumn.cellValueFactory = PropertyValueFactory("y")
        this.creationDateColumn.cellValueFactory = PropertyValueFactory("creationDate")
        this.enginePowerColumn.cellValueFactory = PropertyValueFactory("enginePower")
        this.vehicleTypeColumn.cellValueFactory = PropertyValueFactory("vehicleType")
        this.fuelTypeColumn.cellValueFactory = PropertyValueFactory("fuelType")
        this.usernameColumn.cellValueFactory = PropertyValueFactory("username")
    }
    
    private fun tableSynchronize() {
        for (i in collection){
            this.data.add(VehicleTable(SimpleIntegerProperty(i.id!!), SimpleStringProperty(i.name), SimpleFloatProperty(i.coordinates.x),
                SimpleIntegerProperty(i.coordinates.y), SimpleStringProperty(i.creationDate.toString()), SimpleFloatProperty(i.enginePower),
                SimpleStringProperty(i.type.toString()), SimpleStringProperty(i.fuelType.toString()), SimpleStringProperty(i.username.toString())))
        }
        println(data.count())
        this.table.items = data
    }

    @OptIn(ExperimentalContracts::class)
    private inline fun <T> buttonLoad(buttonName: String, configureController: T.() -> Unit = {}): Stage {
        contract { callsInPlace(configureController, InvocationKind.EXACTLY_ONCE) }
        val stage = Stage()
        val loader = FXMLLoader()
        loader.location = MainSceneController::class.java.getResource("/$buttonName.fxml")
        stage.scene = Scene(loader.load())
        stage.title = buttonName
        loader.getController<T>().configureController()
        stage.show()
        return stage
    }

    @FXML
    private fun signUpHandle() {
        this.buttonLoad<SignUpController>("SignUp")
    }

    @FXML
    private fun logInHandle() {
        val stage = this.buttonLoad<LogInController>("LogIn"){
            this.addReadyButtonHandler(this@MainSceneController.LoginSuccessHandler())
        }
        stage.setOnCloseRequest{
            setComponents()
        }
    }

    @FXML
    private fun addHandle() {
        val stage = this.buttonLoad<AddController>("Add"){
            this.user = this@MainSceneController.user!!
            this.setAddControllerComponentsVisible(idVisible = false, addVisible = true, addIfMinVisible = false, updateVisible = false)
        }
        this.tableSynchronize()
    }

    @FXML
    private fun addIfMinHandle() {
        val stage = buttonLoad<AddController>("AddIfMin") {
            this.user = this@MainSceneController.user!!
            this.setAddControllerComponentsVisible(idVisible = false, addVisible = false, addIfMinVisible = true, updateVisible = false)
        }
        this.tableSynchronize()
    }

    @FXML
    private fun updateHandle() {
        val stage = buttonLoad<AddController>("Update") {
            this.user = this@MainSceneController.user!!
            this.setAddControllerComponentsVisible(idVisible = true, addVisible = false, addIfMinVisible = false, updateVisible = true)
        }
        this.tableSynchronize()
    }

    @FXML
    private fun countByTypeHandle(){
        this.countByType.setOnAction {
            val countByTypeController = CountByTypeController(user!!)
//            val stage = buttonHandle("CountByType", countByTypeController)
        }
    }

    @FXML
    private fun groupCountingByTypeHandle(){
        this.groupCountingByType.setOnAction {
            val groupCountingByTypeController = GroupCountingByTypeController(user!!)
//            val stage = buttonHandle("GroupCountingByType", groupCountingByTypeController)
            groupCountingByTypeController.groupCountingButtonHandle()
        }
    }

    @FXML
    private fun removeByIdHandle(){
        this.removeById.setOnAction {
            val removeByIdController = RemoveByIdController(user!!)
//            val stage = buttonHandle("RemoveById", removeByIdController)
        }
    }

    @FXML
    private fun removeLowerHandle(){
        this.removeLower.setOnAction {
            val removeLowerController = RemoveLowerController(user!!)
//            val stage = buttonHandle("RemoveLower", removeLowerController)
        }
    }

    @FXML
    private fun sumOfEnginePowerHandle(){
        this.sumOfEnginePower.setOnAction {
            val sumOfEnginePowerController = SumOfEnginePowerController(user!!)
//            val stage = buttonHandle("SumOfEnginePower", sumOfEnginePowerController)
        }
    }

    @FXML
    private fun helpHandle() {
        this.help.setOnAction {
            val helpController = HelpController(user!!)
//            val stage = buttonHandle("Help", helpController)
        }
    }

    @FXML
    private fun infoHandle() {
        this.info.setOnAction {
            val infoController = InfoController(user!!)
//            val stage = buttonHandle("Info", infoController)
            infoController.infoButtonHandle()
        }
    }

    @FXML
    private fun removeFirstHandle() {
        this.removeFirst.setOnAction {
            Alert.notification(RequestManager.manage(this.user, RemoveFirst).result)
        }
    }

    @FXML
    private fun clearHandle() {
        this.clear.setOnAction {
            Alert.notification(RequestManager.manage(this.user, Clear).result)
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