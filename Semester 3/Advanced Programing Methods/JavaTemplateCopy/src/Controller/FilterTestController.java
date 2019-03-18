package Controller;

import Service.Service;
import Utils.Observer;
import Utils.ServiceEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class FilterTestController implements Observer<ServiceEvent> {

    @FXML
    ChoiceBox<String> choiceBox1, choiceBox2;




    private Service service;
    //private ObservableList<Entity> listModel;

    public void setService(Service service){
        this.service = service;
    }


    private void pupulateList(){
        //service.getall();
        //list.foreach(x->listModel.add(x))
    }

    @FXML
    private void initialize(){

        choiceBox1.getItems().addAll("none","a","b","c","d");
        choiceBox2.getItems().addAll("none","1","2","3","4");

        choiceBox1.setValue("none");
        choiceBox2.setValue("none");
    }


    @Override
    public void notify(ServiceEvent event) {
        //
    }
}
