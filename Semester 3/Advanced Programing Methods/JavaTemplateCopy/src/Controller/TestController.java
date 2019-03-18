package Controller;

import Service.Service;
import Utils.Observer;
import Utils.ServiceEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class TestController implements Observer<ServiceEvent> {

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
    private void initialize(){}


    @Override
    public void notify(ServiceEvent event) {
        //
    }
}
