package Controller;

import Utils.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.util.ArrayList;

public class RaportController<Student> extends TemplateController {

    @FXML
    TextArea txtArea;

    @Override
    protected void addThisToServiceList() {
        this.service.addObserver(this);
    }

    @Override
    public void updateFields(Object newValue) {}

    @Override
    public Object getEntityFromFields() {
        return null;
    }

    @Override
    public void populateList() {
        txtArea.setText("Hello world!");
        controllerModel.clear();
        ArrayList<AppSim.Student> list;//=service.getraport()
        //list.forEach(x->this.controllerModel.add(x));
    }

    @Override
    public void notify(Event event) {
        this.populateList();
    }
}
