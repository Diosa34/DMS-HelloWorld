//package com.github.diosa.dms;
//
//import com.github.diosa.dms.commandHandle.ButtonController;
//import com.github.diosa.dms.commandHandle.LogInController;
//import com.github.diosa.dms.commandHandle.SignUpController;
//import com.github.diosa.dms.users.User;
//import com.jfoenix.controls.JFXButton;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class MainSceneController {
//    private User user;
//    @FXML
//    public Label nick;
//    @FXML
//    private JFXButton signUpBtn;
//    @FXML
//    private JFXButton logInBtn;
//
//    @FXML
//    private JFXButton addIfMin;
//    @FXML
//    private JFXButton clear;
//    @FXML
//    private JFXButton removeById;
//    @FXML
//    private JFXButton removeLower;
//    @FXML
//    private JFXButton update;
//    @FXML
//    private JFXButton countByType;
//    @FXML
//    private JFXButton groupCountingByType;
//    @FXML
//    private JFXButton removeFirst;
//    @FXML
//    private JFXButton sumOfEnginePower;
//    @FXML
//    private JFXButton add;
//
//    @FXML
//    private Stage buttonHandle(String buttonName, ButtonController controller){
//        Stage stage = new Stage();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(MainSceneController.class.getResource("/" + buttonName + ".fxml"));
//        try{
//            stage.setScene(new Scene(loader.load()));
//        } catch (IOException ex){
//            System.out.println(ex.getMessage());
//        }
//        stage.setTitle(buttonName);
//
//        loader.setController(controller);
//        stage.show();
//        return stage;
//    }
//
//    @FXML
//    private void signUpHandle() {
//        this.signUpBtn.setOnAction( e -> buttonHandle("SignUp", new SignUpController()));
//    }
//    @FXML
//    private void logInHandle() {
//        this.logInBtn.setOnAction( e -> {
//            LogInController logInController = new LogInController();
//            Stage stage = buttonHandle("LogIn", logInController);
//
//            stage.setOnCloseRequest(event -> {
//                this.user = logInController.getUser();
//                setComponents();
//                this.signUpBtn.setVisible(false);
//                this.logInBtn.setVisible(false);
//                this.add.setVisible(true);
//                this.addIfMin.setVisible(true);
//                this.clear.setVisible(true);
//                this.countByType.setVisible(true);
//                this.groupCountingByType.setVisible(true);
//                this.removeById.setVisible(true);
//                this.removeFirst.setVisible(true);
//                this.removeLower.setVisible(true);
//                this.sumOfEnginePower.setVisible(true);
//                this.update.setVisible(true);
//            });
//        });
//    }
//
//    private void setComponents(){
//        if (this.user != null){
//            this.nick.setText(this.user.getLogin());
//            this.nick.setVisible(true);
//            //перенести сюда изменение видимости кнопок
//        }
//    }
//}
