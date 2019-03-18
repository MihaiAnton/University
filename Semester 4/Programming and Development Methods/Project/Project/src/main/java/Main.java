import Repository.*;
import Service.Service;

import java.io.FileInputStream;
import java.util.Properties;

public class Main /*extends Application */{
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("Login");
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/LoginScreenFXML.fxml"));
//        Pane pane = (Pane) loader.load();
//
//        LoginController loginController = loader.getController();
//        loginController.setService(getService());
//        loginController.setStage(primaryStage);
//        Scene scene = new Scene(pane);
//        primaryStage.setScene(scene);
//
//        primaryStage.show();
//    }

    public static void main(String[] args) {
        Service service = getService();
    }

    private static Service getService(){
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("bd.config"));
        } catch (Exception e) {}


        RegistrationRepo registrationRepo =new RegistrationRepo("registrations",props);
        AthleteRepo athleteRepo = new AthleteRepo("athletes",props);
        RaceTypeRepo raceTypeRepo = new RaceTypeRepo("racetypes",props);
        UserRepo userRepo = new UserRepo("users",props);
        CredentialsRepo credentialsRepo = new CredentialsRepo("credentials", props);

        Service service = new Service(registrationRepo,athleteRepo,raceTypeRepo,userRepo,credentialsRepo);

        return service;
    }
}
