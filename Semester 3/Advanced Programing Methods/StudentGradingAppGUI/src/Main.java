
import Domain.Grade;
import GUI.Views.MainView;
import Repository.GradeXMLRepository;
import Repository.HomeworkXMLRepository;
import Repository.StudentXMLRepository;
import Service.TeacherService;
import Validators.GradeValidator;
import Validators.HomeworkValidator;
import Validators.StudentValidator;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Controller.MainController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Student Grading Application");
        BorderPane pane=getView();
        Scene scene = new Scene(pane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static BorderPane getView(){



        String studentFile = "M:\\School\\Metode Avansate de Programare\\StudentGradingAppGUI\\src\\DataFiles\\Studenti.XML";
        String homeworkFile = "M:\\School\\Metode Avansate de Programare\\StudentGradingAppGUI\\src\\DataFiles\\Homework.XML";
        String gradeFile = "M:\\School\\Metode Avansate de Programare\\StudentGradingAppGUI\\src\\DataFiles\\Grades.XML";

        StudentXMLRepository studentRepo = new StudentXMLRepository(new StudentValidator(), studentFile);
        HomeworkXMLRepository homeworkRepo = new HomeworkXMLRepository(new HomeworkValidator(), homeworkFile);
        GradeXMLRepository gradeRepo = new GradeXMLRepository(new GradeValidator(), gradeFile);

        TeacherService service = new TeacherService(studentRepo, homeworkRepo, gradeRepo);

        MainController controller = new MainController(service);
        MainView mainView = new MainView(controller,service);
        //UI ui = new UI(service);
        //ui.run();
        gradeRepo.save(new Grade("1",2,3,5.5));

        return mainView.getView();


    }










    public static void main(String[] args) {

        //Shape s = new Square("Awesome square.", 3.14);
        //System.out.println(s.Area());
        launch(args);
    }



    }
