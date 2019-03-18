package GUI.Views;

import Controller.StudentController;
import Domain.Homework;
import Domain.Student;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentView {

    BorderPane pane;
    StudentController controller;
    TextField id, name, group, mail, teacher, gradetext, homeworktext, weektext, studenttext, feedbackField;
    RadioButton motivated;

    private TableView<Student> table = new TableView<>();

    public StudentView(StudentController controller){
        this.controller = controller;
        initView();
    }

    private void initView(){
        this.pane = new BorderPane();
        pane.setCenter(getTable());
        pane.setRight(getActions());

    }


    private Node getActions() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(15,15,15,15));

        Text sceneTitle = new Text("Student");
        pane.add(sceneTitle, 0,0,2,1);

        //id
        Label lbl1 = new Label("Id:");
        pane.add(lbl1,0,1);
        id = new TextField();
        pane.add(id,1,1);

        //name
        Label lbl2 = new Label("Name:");
        pane.add(lbl2,0,2);
        name = new TextField();
        pane.add(name,1,2);

        //group
        Label lbl3 = new Label("Group:");
        pane.add(lbl3,0,3);
        group = new TextField();
        pane.add(group,1,3);

        //mail
        Label lbl4 = new Label("Mail:");
        pane.add(lbl4,0,4);
        mail = new TextField();
        pane.add(mail,1,4);

        //teacher
        Label lbl5 = new Label("Teacher:");
        pane.add(lbl5,0,5);
        teacher = new TextField();
        pane.add(teacher,1,5);



        //buttons
        Button add = new Button("Add");
        add.setOnAction(x->handleAdd());
        Button update = new Button("Update");
        update.setOnAction(x->handleUpdate());
        Button delete = new Button("Delete");
        delete.setOnAction(x->handleDelete());
        Button clear = new Button("Clear");
        clear.setOnAction(x->clearFields());

        HBox btns = new HBox(10);
        btns.setAlignment(Pos.BOTTOM_CENTER);
        btns.getChildren().addAll(add,update,delete,clear);
        pane.add(btns,0,6,2,1);


        //grading
        Button grade = new Button("Grade");
        grade.setOnAction(x->gradeHandle());
        gradetext = new TextField();
        homeworktext = new TextField();
        weektext = new TextField();
        studenttext = new TextField();
        Label studentlabel = new Label("Students:");
        Label gradeLabel = new Label("Grade:");
        Label homeworkLabel = new Label("Homework:");
        Label weekLabel = new Label("Week");

        VBox gradingBox = new VBox(10);
        HBox homeworkBox = new HBox();
        homeworkBox.getChildren().addAll(homeworkLabel, homeworktext);

        HBox studentsBox = new HBox();
        studentsBox.getChildren().addAll(studentlabel,studenttext);

        HBox gradeBox = new HBox();
        gradeBox.getChildren().addAll(gradeLabel, gradetext);

        HBox weekBox = new HBox();
        weekBox.getChildren().addAll(weekLabel, weektext);

        HBox feedBackBox = new HBox();
        Label feedbacklabel = new Label("Feedback:");
        feedbackField = new TextField();
        feedBackBox.getChildren().addAll(feedbacklabel, feedbackField);

        HBox motivatedHBox = new HBox();
        motivated = new RadioButton("Has missing reason.");
        motivatedHBox.getChildren().addAll(motivated);

        gradingBox.getChildren().addAll(studentsBox, homeworkBox, gradeBox, weekBox, feedBackBox,motivatedHBox, grade);
        pane.add(gradingBox,0,8,2,1);


        return pane;
    }

    private void gradeHandle() {
        try{
            String students = studenttext.getText();

            List<String> studIds = Arrays.asList(students.split(","));

            double grade = Double.parseDouble(gradetext.getText());
            int hid = Integer.parseInt(homeworktext.getText());

            int week = Integer.parseInt(weektext.getText());


            for (String studId:studIds) {

                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                String message = "Assign grade " + grade + " to student " + studId + " at homework " + hid;
                confirmation.setTitle("Grade confirmation");
                confirmation.setContentText(message);
                confirmation.showAndWait();
                ButtonType result = confirmation.getResult();

                if(result.getText().equals("OK")){
                    if(motivated.isSelected()){
                        controller.gradeStudent(studId, hid, grade, -1);
                    }
                    else{
                        controller.gradeStudent(studId, hid, grade, week);
                    }
                }
            }


        }
        catch(Exception e){
            showErrorMessage(e.getMessage());
        }

    }

    private Student getStudentFromFields(){
        String sid = id.getText();
        String sname = name.getText();
        String semail = mail.getText();
        String steacher = teacher.getText();
        try{
            int sgroup = Integer.parseInt(group.getText());
            Student s = new Student(sid,sname,sgroup,semail,steacher);
            return s;
        }
        catch(Exception e){
            throw new RuntimeException("Invalid data.");
        }
    }

    private void handleAdd(){

        try{
            Student s = getStudentFromFields();
            controller.addStudent(s);

        }
        catch(Exception e){
            showErrorMessage(e.getMessage());
        }
    }



    private void handleUpdate(){
        try{
            Student s = getStudentFromFields();
            controller.updateStudent(s);

        }
        catch(Exception e){
            showErrorMessage(e.getMessage());
        }

    }

    private void handleDelete(){
        try{
            Student s = getStudentFromFields();
            controller.removeStudent(s);

        }
        catch(Exception e){
            showErrorMessage(e.getMessage());
        }
    }

    public BorderPane getView(){
        return this.pane;
    }

    private StackPane getTable(){
        StackPane pane = new StackPane();
        initTableView();

        pane.getChildren().add(table);
        return pane;
    }

    private void initTableView(){
        TableColumn<Student, String> idStud = new TableColumn<>("Id");
        TableColumn<Student, String> studName = new TableColumn<>("Name");
        TableColumn<Student, Integer> studGroup = new TableColumn<>("Group");
        TableColumn<Student, String> studMail = new TableColumn<>("Mail");
        TableColumn<Student, String> studTeacher = new TableColumn<>("Teacher");

        table.getColumns().addAll(idStud,studName,studGroup,studMail,studTeacher);


        idStud.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        studName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        studGroup.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
        studMail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        studTeacher.setCellValueFactory(new PropertyValueFactory<Student, String>("teacher"));

        table.setItems(controller.getStudentModel());

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)->showStudentDetails(newValue));
    }

    private void showStudentDetails(Student student){
        if(student == null){
            clearFields();
        }
        else{
            id.setText(student.getId());
            name.setText(student.getName());
            group.setText("" + student.getGroup());
            mail.setText(student.getEmail());
            teacher.setText(student.getTeacher());
        }
    }

    private void clearFields() {
        id.setText("");
        name.setText("");
        group.setText("");
        mail.setText("");
        teacher.setText("");
    }

    private void showErrorMessage(String message) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error message");
        msg.setContentText(message);
        msg.showAndWait();
    }

}







































