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

    private void setMainStage(){
        try {
            mainStage = new Stage();
            mainStage.setTitle("Swim contest");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI/MainScreen.fxml"));
            Pane mainPane = (Pane) fxmlLoader.load();

            MainController mainController = fxmlLoader.getController();
            mainController.setStage(mainStage);

            Scene scene = new Scene(mainPane);
            mainStage.setScene(scene);
        }
        catch(Exception e){
        }

    }

    @FXML
    public void initialize(){
        setMainStage();
    }

    @FXML
    public void handleLogIn(ActionEvent actionEvent) {
        int userId = Integer.parseInt(userText.getText());
        String password = passText.getText();

        boolean logInStatus = service.logIn(userId, password);
        if(logInStatus == true){
            this.stage.close();
            this.mainStage.show();
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

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
}
