package Controller;

import AppSim.Student;
import Service.Service;
import Utils.Event;
import Utils.Observer;
import Utils.ServiceEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TestController<Student> extends CRUDTemplateController {




    @Override
    protected void addThisToServiceList() {
        this.service.addObserver(this);
    }

    @Override
    public void updateFields(Object newValue) {
        AppSim.Student s = (AppSim.Student)newValue;
        tf1.setText(s.getId());
        tf2.setText(""+s.getGroup());
        tf3.setText(s.getName());
        tf4.setText(s.getTeacher());
        tf5.setText(s.getMail());
    }

    @Override
    public Object getEntityFromFields() {
        String id  = tf1.getText();
        int group = Integer.parseInt(tf2.getText());
        String name = tf3.getText();
        String t = tf4.getText();
        String mail = tf5.getText();

        return new AppSim.Student(id, name, mail, t, group);
    }

    @Override
    public void populateList() {
        this.controllerModel.clear();
        for (AppSim.Student s:
             service.getEntity1Iterable()) {
            this.controllerModel.add(s);
        }
    }


}
