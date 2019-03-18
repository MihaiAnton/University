package Service;

import Domain.*;
import Repository.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *  The apps business logic layer
 */
public class Service {

    private RegistrationRepo registrationRepo;
    private AthleteRepo athleteRepo;
    private RaceTypeRepo raceTypeRepo;
    private UserRepo userRepo;
    private CredentialsRepo credentialsRepo;

    public Service(RegistrationRepo registrationRepo,
                   AthleteRepo athleteRepo,
                   RaceTypeRepo raceTypeRepo,
                   UserRepo userRepo,
                   CredentialsRepo credentialsRepo) {

        this.registrationRepo = registrationRepo;
        this.athleteRepo = athleteRepo;
        this.raceTypeRepo = raceTypeRepo;
        this.userRepo = userRepo;
        this.credentialsRepo = credentialsRepo;
    }


    /**
     * logs in a user
     * @param userId : the id of the user
     * @param password : the password entered by the user
     * @return true if successfully logged in
     *         false otherwise
     */
    public boolean logIn(int userId, String password){
        Credentials credentials = credentialsRepo.findOne(userId);
        if(credentials == null || !(password.equals(credentials.getPassword()))){
            return false;
        }
        return true;
    }

    /**
     * logs out a user
     * @param userId : the id of the user
     */
    public void logOut(int userId){
    }

    /**
     * Returns all the race typesin the database
     * @return a list of race types
     */
    public Iterable<RaceType> getRaceTypes(){
        return raceTypeRepo.findAll();
    }

    /**
     * Returns the athletes that registered at a race
     * @param length
     * @param style
     * @return
     */
    public Iterable<Athlete> getAthletesByRace(RaceLength length, Style style) {
        Pair<RaceLength, Style> id = new Pair<RaceLength, Style>() {
            @Override
            public RaceLength getFirst() {
                return super.getFirst();
            }
        };

        id.setFirst(length);
        id.setSecond(style);

        List<Athlete> list = new ArrayList<>();
        RaceType raceType = raceTypeRepo.findOne(id);
        if(raceType == null){
            return null;
        }

        for (Registration r:registrationRepo.findAll()) {
            if(r.getRaceId().getSecond() == style && r.getRaceId().getFirst() == length) {
                Athlete athlete = athleteRepo.findOne(r.getAthleteId());
                if (athlete != null) {
                    list.add(athlete);
                }
            }
        }

        return list;
    }

    /**
     * Returns the races that an athlete will attend
     * @param athleteId
     * @return a list of RaceTypes;
     */
    public Iterable<RaceType> getRacesByAthele(int athleteId){
        Athlete athlete = athleteRepo.findOne(athleteId);
        if(athlete == null){
            return null;
        }

        List<RaceType> raceTypeList = new ArrayList<>();

        for (Registration r:registrationRepo.findAll()) {
            if(r.getAthleteId() == athleteId){
                raceTypeList.add(raceTypeRepo.findOne(r.getRaceId()));
            }
        }

        return raceTypeList;
    }


    /**
     * Requests the data for the athlete and adds it to the database;
     * @param athleteId
     * @param name
     * @param birthDate
     */
    public void registerAthlete(int athleteId, String name, Date birthDate){
        if(athleteRepo.findOne(athleteId) != null){
            throw new RuntimeException("Id not available");
        }

        else{
            Athlete athlete = new Athlete(athleteId, name, birthDate);
            try {
                athleteRepo.save(athlete);
            }
            catch (Exception e){
                throw new RuntimeException("Error updating the database");
            }
        }
    }

    /**
     * Registers the athlete to a list of races;
     * @param athleteId
     * @param races
     * @return
     */
    public int addRacesToAthlete(int athleteId, Iterable<RaceType> races){
        Athlete athlete = athleteRepo.findOne(athleteId);
        if(athlete == null){
            throw new RuntimeException("Athlete not existing");
        }

        int added = 0;
        for(RaceType raceType : races){
            if(raceTypeRepo.findOne(raceType.getId()) == null){
                continue;
            }
            else{

                Registration registration = new Registration(athleteId, raceType.getLen(), raceType.getStyle());
                if(registrationRepo.findOne(registration.getId()) != null){
                    continue;
                }
                else{
                    added = added + 1;
                    registrationRepo.save(registration);
                }
            }
        }
        return added;
    }

}





















