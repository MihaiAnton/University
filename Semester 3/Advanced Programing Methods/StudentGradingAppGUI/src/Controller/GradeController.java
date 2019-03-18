package Controller;

import Domain.Grade;
import Domain.Student;
import Service.TeacherService;
import Utils.Groups;
import Utils.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class GradeController {

    private TeacherService service;

    ObservableList<Grade> gradeModel;


    @FXML
    ChoiceBox<Groups> groupCB;

    @FXML
    ChoiceBox<Integer> homeworkCb;

    @FXML
    TextField studentField;

    @FXML
    TableView table;

    @FXML
    Button filterButton;

    public GradeController(){}

    public void setService(TeacherService service) {

        this.service = service;

    }

    @FXML
    private void initialize() {
        gradeModel = FXCollections.observableArrayList();
        this.table.setItems(gradeModel);
        setGroupChoices();
        setHomeworkChoices();
        clearModel();
        clearFields();


    }


    private void setGroupChoices(){

        groupCB.getItems().add(Groups.None);
        groupCB.getItems().add(Groups.Gr221);
        groupCB.getItems().add(Groups.Gr222);
        groupCB.getItems().add(Groups.Gr223);
        groupCB.getItems().add(Groups.Gr224);
        groupCB.getItems().add(Groups.Gr225);
        groupCB.getItems().add(Groups.Gr226);
        groupCB.getItems().add(Groups.Gr227);

        groupCB.getSelectionModel().selectFirst();

    }

    private void setHomeworkChoices(){

        int i;
        homeworkCb.getItems().add(0);
        for(i=1;i<=14;i++){
            homeworkCb.getItems().add(i);
        }
        homeworkCb.setValue(0);

    }

    private void clearModel(){
        gradeModel.clear();
    }

    private void clearFields(){
        studentField.clear();
    }

    private String parseGroup(String grade){

        return grade.substring(2);
    }


    private Iterable<Grade> applyFilters(Iterable<Grade> list){



        //homework id
        ArrayList<Grade> filtered1 = new ArrayList<>();
        for (Grade g: list) {
            if(homeworkCb.getValue() == 0 || homeworkCb.getValue() == g.getHomeworkId()){
                filtered1.add(g);
            }
        }

        //student id
        ArrayList<Grade> filtered2 = new ArrayList<>();
        for (Grade g: filtered1) {
            if (studentField.getText() == null || studentField.getText().equals("") || g.getStudId().matches(studentField.getText() + ".*")) {//studentField.getText().equals(g.getStudId())){

                filtered2.add(g);
            }
        }


        //group
        ArrayList<Grade> filtered3 = new ArrayList<>();
        for (Grade g: filtered2) {
            if(groupCB.getValue().toString().equals("None") || Integer.parseInt(parseGroup(groupCB.getValue().toString())) == g.getStudGroup()){
                filtered3.add(g);
            }
        }

        return filtered3;

    }


    @FXML
    private void filterHandle(){
        //get all grades

        clearModel();

        Iterable<Grade> allgrades = service.getAllGrades();
        allgrades.forEach((x)-> {
                    Student student = service.findStudent(x.getStudId());
                    if (student != null) {
                        x.setStudGroup(student.getGroup());
                        x.setStudName(student.getName());
                        x.setStudTeacher(student.getTeacher());
                        x.setStudentId(student.getId());

                    }
                }
        );


        Iterable<Grade> filtered = applyFilters(allgrades);

        //set ok grades
        filtered.forEach((x)->{
            Student student = service.findStudent(x.getStudId());
            if(student != null) {
                x.setStudGroup(student.getGroup());
                x.setStudName(student.getName());
                x.setStudTeacher(student.getTeacher());
                x.setStudentId(student.getId());
                gradeModel.add(x);
            }
        });
    }




}





































