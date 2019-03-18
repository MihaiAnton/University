package Repository;

import Domain.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Properties;

public class RegistrationRepo extends DataBaseRepo<Domain.Pair<Integer, RaceTypeLenPair>, Registration> {

    public RegistrationRepo(String tableName, Properties properties) {
        super(tableName, properties);
    }

    @Override
    String getInsertStatement(Registration entity) {
        return "INSERT INTO registrations(athleteId, length, style) " +
                "VALUES (" + entity.getAthleteId() + ",'" +entity.getRaceId().getFirst().toString() + "','" +entity.getId().getSecond().getSecond().toString()+"')";
    }

    @Override
    String getUpdateStatement(Registration entity) {
        return "UPDATE registrations " +
                "SET athleteId = " + entity.getAthleteId() +", length = '" + entity.getRaceId().getFirst().toString() + "' , style = '"+ entity.getId().getSecond().getSecond().toString() + "' " +
                "WHERE athleteId = " + entity.getAthleteId() +" and length = '" + entity.getRaceId().getFirst().toString() + "'";
    }

    @Override
    String getDeleteStatement(Pair<Integer, RaceTypeLenPair> integerRaceTypeLenPairPair) {

        return "DELETE FROM registrations" +
               "WHERE athleteId = " + integerRaceTypeLenPairPair.getFirst() +"and raceId = " + integerRaceTypeLenPairPair.getSecond() + " and style = '" + integerRaceTypeLenPairPair.getSecond().getSecond().toString() + "'";
    }

    @Override
    String getSelectStatement(Pair<Integer, RaceTypeLenPair> integerRaceTypeLenPairPair) {
        return "SELECT athleteId,raceId FROM registrations " +
                "WHERE athleteId = " + integerRaceTypeLenPairPair.getFirst() +" and raceId = " + integerRaceTypeLenPairPair.getSecond() + " and style = '" + integerRaceTypeLenPairPair.getSecond().getSecond().toString() + "'";
    }

    @Override
    Registration getEntity(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("athleteId");
                String length = resultSet.getString("length");
                String style = resultSet.getString("style");
                return new Registration(id, RaceLength.valueOf(length),Style.valueOf(style));
            }
        }
        catch (Exception e){
            Logger logger = LogManager.getLogger("DataBase Logger");
            logger.error("Exception in registrations repo getEntity method.");
            return null;
        }
        return null;
    }
}
