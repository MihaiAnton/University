package UI;

import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public abstract class TemplateController<E>{

    @FXML
    protected TableView<E> table;

    private Stage thisStage;

    public void setStage(Stage stage){
        this.thisStage = stage;
    }




    protected Service service;
    protected ObservableList<E> controllerModel = null;

    public void setService(Service service){
        this.service = service;
        //populateList();
    }


    @FXML
    public void initialize(){

        controllerModel = FXCollections.observableArrayList();
        table.setItems(controllerModel);

        this.table.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> {
            this.updateFields(newValue);
        });
    }

    public abstract void updateFields(E newValue);

    public abstract E getEntityFromFields();

    public abstract void populateList();

    public void handleError(String err){
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error message");
        msg.setContentText(err);
        msg.showAndWait();
    }

}