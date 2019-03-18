package UI;

import Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    public Button logInBtn;

    @FXML
    public TextField userText;

    @FXML
    public PasswordField passText;

    private Service service;
    private Stage mainStage;
    private Stage stage;


    public void setService(Service service){
        this.service = service;

    }

    @FXML
    public void initialize(){
    }

    @FXML
    public void handleLogIn(ActionEvent actionEvent) {

        if(userText.getText().equals("")){
            return;
        }


        int userId = Integer.parseInt(userText.getText());
        String password = passText.getText();

        System.out.println(userId);
        System.out.println(password);

        boolean logInStatus = service.logIn(userId, password);
        if(logInStatus == true){
            this.mainStage.show();
            this.stage.close();
        }
        else{
            loginError("Wrong username or password");
        }

    }

    public void loginError(String text){
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Login error");
        msg.setContentText(text);
        msg.showAndWait();
    }

    public void setStage(Stage primaryStage, Stage nexrStage) {
        this.stage = primaryStage;
        mainStage =nexrStage;
    }
}
