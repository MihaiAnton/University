import AppSim.Student;
import AppSim.StudentRepo;
import AppSim.StudentTxtRepo;
import Controller.MainController;
import Domain.Client;
import Repository.ClientRepository;
import Repository.LaptopRepository;
import Repository.SaleRepository;
import Service.Service;
import Utils.DateManager;
import Validators.GenericValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    public static String[] mainArgs;

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(mainArgs[0]);
        stage.setTitle("Exam Application");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/MainView.fxml"));
        Pane pane = (Pane) loader.load();
        MainController controller = loader.getController();
        Service service = getService();
        service.setDateParam(mainArgs[0]);
        controller.setService(service);
        Scene mainScene = new Scene(pane);
        stage.setScene(mainScene);

        stage.show();
    }

    private Service getService() {

        //repo files "M:\School\Metode Avansate de Programare\JavaExam\src\StoredDataFiles"


        //repositories
        ClientRepository clientRepository = new ClientRepository(new GenericValidator(), "M:\\School\\Metode Avansate de Programare\\JavaExam\\src\\StoredDataFiles\\clients.txt");
        LaptopRepository laptopRepository = new LaptopRepository(new GenericValidator(), "M:\\School\\Metode Avansate de Programare\\JavaExam\\src\\StoredDataFiles\\laptops.txt");
        SaleRepository saleRepository = new SaleRepository(new GenericValidator(), "M:\\School\\Metode Avansate de Programare\\JavaExam\\src\\StoredDataFiles\\sales.txt");

        Service service = new Service(clientRepository, laptopRepository, saleRepository);
        return service;
    }

    public static void main(String[] args) {
        mainArgs = args;
        launch(args);
    }


}
