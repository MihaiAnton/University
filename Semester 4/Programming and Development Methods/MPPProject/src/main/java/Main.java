import Domain.AthleteRaceDTO;
import Repository.*;
import Service.Service;
import UI.LoginController;
import UI.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Properties;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/LoginScreenFXML.fxml"));
        Pane pane = (Pane) loader.load();

        Service service = getService();

        LoginController loginController = loader.getController();
        loginController.setService(service);

        Stage mainStage = getMainStage(service);
        System.out.println(mainStage);
        loginController.setStage(primaryStage,mainStage);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private Stage getMainStage(Service service) {


        try {
            Stage mainStage;
            mainStage = new Stage();
            mainStage.setTitle("Swim contest");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI/MainScreen.fxml"));
            Pane mainPane = (Pane) fxmlLoader.load();

            MainController mainController = fxmlLoader.getController();
            mainController.setStage(mainStage);
            mainController.setService(service);

            Scene scene = new Scene(mainPane);
            mainStage.setScene(scene);

            return mainStage;
        }
        catch(Exception e){
            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            System.out.println(e.getMessage());
            return null;
        }
    }



    public static void main(String[] args) {
        launch(args);
    }


    private static Service getService(){
        Properties props = new Properties();
        props.setProperty("tasks.jdbc.driver","com.mysql.jdbc.Driver");
        props.setProperty("tasks.jdbc.url","jdbc:mysql://localhost:3306/mppproject");
        props.setProperty("tasks.jdbc.user","root");
        props.setProperty("tasks.jdbc.pass","password");
//        try {
//            props.load(new FileInputStream("database.config"));
//        } catch (Exception e) {
//
//        }

        RegistrationRepo registrationRepo =new RegistrationRepo("registrations",props);
        AthleteRepo athleteRepo = new AthleteRepo("athletes",props);
        RaceTypeRepo raceTypeRepo = new RaceTypeRepo("racetypes",props);
        UserRepo userRepo = new UserRepo("users",props);
        CredentialsRepo credentialsRepo = new CredentialsRepo("credentials", props);

        Service service = new Service(registrationRepo,athleteRepo,raceTypeRepo,userRepo,credentialsRepo);

        return service;
    }
}
