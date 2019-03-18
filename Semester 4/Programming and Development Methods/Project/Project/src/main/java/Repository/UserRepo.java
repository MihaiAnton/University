package Repository;

import Domain.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.Properties;

public class UserRepo extends DataBaseRepo<Integer, User> {
    public UserRepo(String tableName, Properties properties) {
        super("users", properties);
    }

    @Override
    String getInsertStatement(User entity) {
        return "INSERT INTO users(id, name) " +
                "values (" + entity.getId().toString() + ",'" + entity.getName() + "')";
    }

    @Override
    String getUpdateStatement(User entity) {
        return "UPDATE users " +
               "SET name = '" + entity.getName() +
               "' WHERE id = " + entity.getId();
    }

    @Override
    String getDeleteStatement(Integer integer) {
        return "DELETE FROM users " +
               "WHERE id = " + integer.toString();
    }

    @Override
    String getSelectStatement(Integer integer) {
        return "SELECT id, name FROM users " +
               "WHERE id = " + integer.toString();

    }

    @Override
    User getEntity(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new User(id, name);
            }
        }
        catch (Exception e){
            Logger logger = LogManager.getLogger("DataBase Logger");
            logger.error("Exception in user repo getEntity method.");
            return null;
        }
        return null;
    }
}
