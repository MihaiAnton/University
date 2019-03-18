package Repository;

import Domain.Credentials;
import Domain.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.Properties;

public class CredentialsRepo extends DataBaseRepo<Integer, Credentials> {
    public CredentialsRepo(String tableName, Properties properties) {
        super("credentials", properties);
    }

    @Override
    String getInsertStatement(Credentials entity) {
        return "INSERT INTO credentials (userId, password) " +
                "VALUES (" + entity.getId().toString() + ",'" + entity.getPassword() + "')";
    }

    @Override
    String getUpdateStatement(Credentials entity) {
        return "UPDATE credentials " +
               "SET password ='" + entity.getPassword() +
               "' WHERE userId = " + entity.getId();
    }

    @Override
    String getDeleteStatement(Integer integer) {
        return "DELETE FROM credentials " +
                "WHERE userId = " + integer.toString();
    }

    @Override
    String getSelectStatement(Integer integer) {
        return "SELECT userId, password FROM credentials " +
                "WHERE userId = " + integer.toString();
    }

    @Override
    Credentials getEntity(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("userId");
                String name = resultSet.getString("password");
                return new Credentials(id, name);
            }
        }
        catch (Exception e){
            Logger logger = LogManager.getLogger("DataBase Logger");
            logger.error("Exception in credentials repo getEntity method.");
            return null;
        }
        return null;
    }
}
