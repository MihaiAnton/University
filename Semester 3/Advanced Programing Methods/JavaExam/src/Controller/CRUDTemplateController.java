package Controller;

import AppSim.Student;
import Utils.Event;
import Utils.ServiceEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class CRUDTemplateController<E> extends TemplateController {


    @FXML
    TextField tf1, tf2, tf3, tf4, tf5;


    @FXML
    public void handleAdd(){
        try {
            this.service.addEntity1(getEntityFromFields());
            this.service.notifyObserver(new ServiceEvent());
        }
        catch(Exception e){
            handleError(e.getMessage());
        }
    }

    @FXML
    public void handleUpdate(){
        try {
            this.service.updateEntity1(getEntityFromFields());
            this.service.notifyObserver(new ServiceEvent());
        }
        catch (Exception e){
            handleError(e.getMessage());
        }
    }

    @FXML
    public void handleDelete(){
        try{
            this.service.deleteEntity1(getEntityFromFields());
            this.service.notifyObserver(new ServiceEvent());
        }
        catch(Exception e){
            handleError(e.getMessage());
        }
    }

    @FXML
    public void handleClear(){
        tf1.clear();
        tf2.clear();
        tf3.clear();
        tf4.clear();
        tf5.clear();
    }

    @Override
    public void notify(Event event) {
        this.populateList();
    }

}
