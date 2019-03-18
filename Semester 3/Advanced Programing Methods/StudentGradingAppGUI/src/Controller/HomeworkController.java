package Controller;

import Domain.Homework;
import GUI.Events.ServiceEvent;
import Service.TeacherService;
import Utils.Event;
import Utils.HomeworkEventType;
import Utils.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HomeworkController implements Observable<ServiceEvent> {


    @FXML
    private TableView<Homework> table;

    @FXML
    private TableColumn<Homework, Integer> idc, tweekc, dweekc;

    @FXML
    private TableColumn<Homework, String> descc;

    @FXML
    private TextField tweek, dweek, id,desc;
    private ObservableList<Homework> homeworkModel = null;

    private TeacherService service = null;


    public HomeworkController(){}

    public void setService(TeacherService ser){

        this.service = ser;

        ser.addObservable(this);

        populateList();
    }

    @FXML
    public void initialize(){

        this.homeworkModel = FXCollections.observableArrayList();
        table.setItems(homeworkModel);

        //idc.setCellValueFactory(new PropertyValueFactory<Homework,Integer>("id"));
        //tweekc.setCellValueFactory(new PropertyValueFactory<Homework, Integer>("targetWeek"));
        //dweekc.setCellValueFactory(new PropertyValueFactory<Homework, Integer>("deadlineWeek"));
        //descc.setCellValueFactory(new PropertyValueFactory<Homework, String>("description"));

        this.table.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> {
           this.updateFields(newValue);
        });

    }

    private void updateFields(Homework newValue) {
        id.setText(""+newValue.getId());
        desc.setText(newValue.getDescription());
        tweek.setText(""+newValue.getTargetWeek());
        dweek.setText(""+newValue.getDeadlineWeek());
    }

    private void populateList(){
        List<Homework> list = new ArrayList<>();
        service.getHomeworkIterable().forEach((x)->list.add(x));
        homeworkModel.setAll(list);
    }

    private Homework getHW(){
        return new Homework(
                Integer.parseInt(id.getText()),
                desc.getText(),
                Integer.parseInt(tweek.getText()),
                Integer.parseInt(dweek.getText())
                ,Integer.parseInt(tweek.getText()));
    }



    @FXML
    public void addHandle(ActionEvent e){
        try{
            if(this.service.addHomework(getHW()) == null)
                this.service.notifyObservers(new ServiceEvent(getHW(), HomeworkEventType.ADD));
        }
        catch(Exception err){
            showError(err.getMessage());
        }
    }

    @FXML
    public void deleteHandle(ActionEvent e){
        try{
            if(this.service.deleteHomework(getHW()) != null)
                this.service.notifyObservers(new ServiceEvent(getHW(), HomeworkEventType.DELETE));
        }
        catch (Exception err){
            showError(err.getMessage());
        }
    }

    @FXML
    public void updateHandle(ActionEvent e){
        try{
            service.updateHomework(getHW());
            this.service.notifyObservers(new ServiceEvent(getHW(), HomeworkEventType.UPDATE));
        }
        catch(Exception err){
            showError(err.getMessage());
        }
    }

    @FXML
    public void clearHandle(ActionEvent e){
        clearFields();
    }

    private void clearFields() {
        this.id.setText("");
        desc.setText("");
        tweek.setText("");
        dweek.setText("");
    }


    @FXML
    private void showError(String err){

    }

    @Override
    public void update(ServiceEvent event) {
        if(event.getHtype() == null)
            return;
        switch(event.getHtype()){
            case ADD:{
                homeworkModel.add(event.getHomework());
                break;}
            case UPDATE:{

                int i =0;
                boolean found = false;
                for(i=0;i<homeworkModel.size()&&!found;i++){
                    if(homeworkModel.get(i).getId() == event.getHomework().getId()){
                        homeworkModel.remove(i);
                        found = true;
                    }
                }
                homeworkModel.add(event.getHomework());

                break;}
            case DELETE:{
                int i =0;
                boolean found = false;
                for(i=0;i<homeworkModel.size() && !found;i++){
                    if(homeworkModel.get(i).getId() == event.getHomework().getId()){
                        homeworkModel.remove(i);
                        found = true;
                    }
                }
                break;}
        }
    }
}
