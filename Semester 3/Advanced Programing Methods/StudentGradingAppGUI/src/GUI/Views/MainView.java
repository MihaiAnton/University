package GUI.Views;

import Controller.GradeController;
import Controller.HomeworkController;
import Controller.MainController;
import Service.TeacherService;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainView {

    private MainController controller;

    BorderPane pane;
    Button studentMenu, homeworkMenu, exit, gradingMenu;
    HBox buttonBox;

    Stage studentStage, homeworkStage, gradeStage;

    StudentView studentView;// = new StudentView(new StudentController(controller.getService()));



    public MainView(MainController controller,TeacherService service){

        this.controller = controller;
        initSubScenes(service);
        initView();
    }

    private void initSubScenes(TeacherService service){
       try {
           //create the student window
           studentView = new StudentView(controller.getStudCtrl());
           this.studentStage = new Stage();
           studentStage.setTitle("Student Menu");
           BorderPane pane = studentView.getView();
           Scene studScene = new Scene(pane, 800, 500);
           studentStage.setScene(studScene);




           //create the homework window
           homeworkStage = new Stage();
           homeworkStage.setTitle("Homework Menu");


           FXMLLoader loader = new FXMLLoader(getClass().getResource("HWView.fxml"));
           Pane myPane = (Pane) loader.load();
           HomeworkController hctrl = loader.getController();
           hctrl.setService(service);

           Scene homeworkScene = new Scene(myPane);
           homeworkStage.setScene(homeworkScene);


           //create the grading window
           gradeStage = new Stage();
           gradeStage.setTitle("Grades Menu");

           FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Grading.fxml"));

           Pane gradePane = (Pane) loader1.load();
           GradeController grctrl = loader1.getController();
           grctrl.setService(service);


           Scene gradingScene = new Scene(gradePane);
           gradeStage.setScene(gradingScene);



       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }

    }

    private void initView(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(100);
        grid.setVgap(100);
        grid.setPadding(new Insets(180, 180, 180, 180));

        GridPane exitgrid = new GridPane();
        grid.setAlignment(Pos.BOTTOM_CENTER);
        grid.setHgap(50);
        grid.setVgap(50);
        grid.setPadding(new Insets(100, 100, 100, 100));

        pane = new BorderPane();
        studentMenu = new Button("Student Menu");
        homeworkMenu = new Button("Homework Menu");
        gradingMenu = new Button("Grading Menu");
        //set button actions
        studentMenu.setOnAction(x->openStudentScene());
        homeworkMenu.setOnAction(x->openHomeworkScene());
        gradingMenu.setOnAction(x->openGradingScene());

        //add the buttons to the grid
        buttonBox = new HBox();
        buttonBox.getChildren().addAll(studentMenu, homeworkMenu, gradingMenu);

        grid.add(buttonBox,0,1,2,5);
        pane.setCenter(grid);

        //add the exit button
        exit = new Button("Exit");

        HBox exitBox = new HBox();
        exitBox.getChildren().addAll(exit);
        exitgrid.add(exitBox,1,2,3,4);
        pane.setBottom(exitgrid);
    }

    private void openGradingScene() {
        gradeStage.show();
    }

    public BorderPane getView(){

        return this.pane;
    }

    private void openStudentScene(){

        studentStage.show();
    }

    private void openHomeworkScene()
    {
        homeworkStage.show();
    }

}
