package Repository;

import Domain.Athlete;
import Domain.RaceLength;
import Domain.RaceType;
import Domain.Style;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Properties;

public class AthleteRepo extends DataBaseRepo<Integer, Athlete> {
    public AthleteRepo(String tableName, Properties properties) {
        super(tableName, properties);
    }

    @Override
    String getInsertStatement(Athlete entity) {
        return "INSERT INTO athletes(id,name,birthDate) "+
                "VALUES (" + entity.getId() + ",'" + entity.getName() +"','" +
                            entity.getBirthDate().toString() + "')";
    }

    @Override
    String getUpdateStatement(Athlete entity) {
        return "UPDATE athletes " +
                "SET name = '" + entity.getName() + "', " + "birthDate = " + entity.getBirthDate() + "" +
                "WHERE id = " + entity.getId();
    }

    @Override
    String getDeleteStatement(Integer integer) {
        return "DELETE FROM athletes " +
                "WHERE id = " + integer;
    }

    @Override
    String getSelectStatement(Integer integer) {
        return "SELECT id, name, birthDate FROM athletes "+
                "WHERE id = " + integer;
    }

    @Override
    Athlete getEntity(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = resultSet.getString("birthDate");
                return new Athlete(id,name,Date.valueOf(date));
            }
        }
        catch (Exception e){
            Logger logger = LogManager.getLogger("DataBase Logger");
            logger.error("Exception in athletes repo getEntity method.");
            return null;
        }
        return null;
    }
}
