package UI;

import Domain.*;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.Date;

public class MainController extends TemplateController<AthleteRaceDTO> {

    @FXML
    public TextField tf1,tf2,tf3,tf4,tf5;
    private Stage stage;

    @FXML
    public ChoiceBox lencb, stylecb;

    @FXML
    public Text text;

    @FXML
    public void initialize(){
        super.initialize();

    }

    @FXML
    public void setStyleLenCount(){
        int count = service.getStyleCount(Style.valueOf(stylecb.getValue().toString()),RaceLength.valueOf(lencb.getValue().toString()));
        text.setText(count + " athletes.");
    }

    private void setLengthChoices(){
        lencb.getItems().clear();
        for (RaceLength r :
                RaceLength.values()) {
            lencb.getItems().add(r);
        }
        lencb.setValue(RaceLength.L50);
    }

    private void setStyleChoices(){
        stylecb.getItems().clear();
        for(Style s:Style.values()){
            stylecb.getItems().add(s);
        }
        stylecb.setValue(Style.CRAWL);
    }

    @Override
    public void updateFields(AthleteRaceDTO newValue) {

    }

    @Override
    public AthleteRaceDTO getEntityFromFields() {
        return null;
    }

    @Override
    public void populateList() {
        System.out.println("--------------------------------------");
        System.out.println(controllerModel);
        System.out.println(this.service);
        controllerModel.clear();

        for(AthleteRaceDTO athleteRaceDTO:this.service.getAthletesRaceDTO()){
            System.out.println("Added " + athleteRaceDTO.getAthleteName());
            this.controllerModel.add(athleteRaceDTO);
        }
    }

    public void setStage(Stage mainStage) {
        stage = mainStage;
    }

    @FXML
    public void handleRefresh(){
        populateList();
        setStyleChoices();
        setLengthChoices();
        setStyleLenCount();
    }

    @FXML
    public void handleAdd(){
        service.registerAthlete(Integer.parseInt(tf1.getText()),tf2.getText(), Date.valueOf(tf3.getText()));
    }

    @FXML
    public void registerRace(){
        System.out.println("Registering");
        service.addRacesToAthlete(Integer.parseInt(tf1.getText()), new RaceType(RaceLength.valueOf(tf4.getText()), Style.valueOf(tf5.getText())));
    }
}
