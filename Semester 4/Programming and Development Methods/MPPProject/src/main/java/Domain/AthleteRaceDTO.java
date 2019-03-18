package Domain;

import java.util.Date;

public class AthleteRaceDTO {

    private Integer athleteId;
    private String athleteName;
    private Date birtDate;
    private RaceLength raceLen;
    private Style raceStyle;

    public AthleteRaceDTO(Athlete athlete, RaceType raceType){
        this.athleteId = athlete.getId();
        this.athleteName = athlete.getName();
        this.birtDate = athlete.getBirthDate();

        this.raceLen = raceType.getLen();
        this.raceStyle = raceType.getStyle();
    }

    public Integer getAge(){
        return 29;
    }

    public Integer getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Integer athleteId) {
        this.athleteId = athleteId;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public Date getBirthDate() {
        return birtDate;
    }

    public void setBirtDate(Date birtDate) {
        this.birtDate = birtDate;
    }

    public RaceLength getRaceLen() {
        return raceLen;
    }

    public void setRaceLen(RaceLength raceLen) {
        this.raceLen = raceLen;
    }

    public Style getRaceStyle() {
        return raceStyle;
    }

    public void setRaceStyle(Style raceStyle) {
        this.raceStyle = raceStyle;
    }
}
