import Controller.MainController;
import Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Exam Application");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/MainView.fxml"));
        Pane pane = (Pane) loader.load();
        MainController controller = loader.getController();
        controller.setService(getService());
        Scene mainScene = new Scene(pane);
        stage.setScene(mainScene);

        stage.show();
    }

    private Service getService() {

        //repo files "M:\School\Metode Avansate de Programare\JavaExam\src\StoredDataFiles"


        //repositories

        Service service = new Service(/*params*/);
        return service;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
