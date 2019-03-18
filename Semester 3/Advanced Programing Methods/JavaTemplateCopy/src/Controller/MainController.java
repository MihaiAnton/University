package Controller;

import Service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {

    private Service service;
    private TestController testController;
    private FilterTestController filterTestController;

    private Stage leftStage, rightStage;

    public void setService(Service service){

        this.service = service;
        initComponents();
    }

    private void initComponents(){

        try {
            //left stage init
            this.leftStage = new Stage();
            FXMLLoader loaderLeft = new FXMLLoader(getClass().getResource("CRUDTemplateView.fxml"));
            Pane leftPane = (Pane) loaderLeft.load();
            testController = loaderLeft.getController();
            testController.setService(this.service);
            leftStage.setScene(new Scene(leftPane));


            //right stage init
            this.rightStage = new Stage();
            FXMLLoader loaderrRight = new FXMLLoader(getClass().getResource("FilterTemplateView.fxml"));
            Pane rightPane = (Pane) loaderrRight.load();
            filterTestController = loaderrRight.getController();
            filterTestController.setService(this.service);
            rightStage.setScene(new Scene(rightPane));

        }
        catch(Exception e){}
    }

    @FXML
    public void openLeftScene(){
        leftStage.show();
    }

    @FXML
    public void openRightScene(){
        rightStage.show();
    }
}
