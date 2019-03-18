package Repository;

import Domain.IdEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public abstract class DataBaseRepo<ID,E extends IdEntity<ID>> implements CRUDRepo<ID,E> {

    private String tableName;
    private Properties properties;
    private Connection connection;


    public DataBaseRepo(String tableName, Properties properties) {
        this.tableName = tableName;
        this.properties = properties;
        this.connection = getDatabaseConnection();
    }

    abstract String getInsertStatement(E entity);
    abstract String getUpdateStatement(E entity);
    abstract String getDeleteStatement(ID id);
    abstract String getSelectStatement(ID id);
    abstract E getEntity(ResultSet resultSet);

    private Connection getDatabaseConnection(){
        String driver = properties.getProperty("tasks.jdbc.driver");
        String url = properties.getProperty("tasks.jdbc.url");
        String user = properties.getProperty("tasks.jdbc.user");
        String pass = properties.getProperty("tasks.jdbc.pass");

        Connection con = null;
        try{
            if(driver == null){
                System.out.println("---------------------------------error");
            }
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException e) {
            logError("Error loading driver in database repo:" + " " + tableName + " " + properties.getProperty("tasks.jdbc.driver"));
        } catch (SQLException e) {
            logError("SQLException in database repo:" + " " + tableName);
        }
        return con;
    }


    @Override
    public E save(E entity) {
        try(PreparedStatement prSt = connection.prepareStatement(getInsertStatement(entity))){
            int res = prSt.executeUpdate();
            logInfo("Save entity in table " + tableName);
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            logError("SQLException in database repo:" + " " + tableName + "save method.");
            return null;
        }
    }

    @Override
    public E update(E entity) {
        try(PreparedStatement prSt = connection.prepareStatement(getUpdateStatement(entity))){
            int res = prSt.executeUpdate();
            logInfo("Updated entity in table " + tableName);
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            logError("SQLException in database repo:" + " " + tableName + "update method");
            return null;
        }
    }

    @Override
    public E delete(ID id) {
        try(PreparedStatement prSt = connection.prepareStatement(getDeleteStatement(id))){
            int res = prSt.executeUpdate();
            logInfo("Deleted in table " + tableName);
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            logError("SQLException in database repo:" + " " + tableName + "delete method");
            return null;
        }

    }

    @Override
    public E findOne(ID id) {
        try(PreparedStatement prSt = connection.prepareStatement(getSelectStatement(id))){
            //int res = prSt.executeUpdate();
            try(ResultSet res = prSt.executeQuery()){
                logInfo("Found data in table " + tableName);
                return getEntity(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logError("SQLException in database repo:" + " " + tableName + "find one method");
            return null;
        }


    }

    private void logInfo(String s){
        Logger logger = LogManager.getLogger("DataBase Logger");
        logger.info(s);
    }

    private void logError(String s){
        Logger logger = LogManager.getLogger("DataBase Logger");
        logger.error(s);
    }

    @Override
    public Iterable<E> findAll(){
        try(PreparedStatement prSt = connection.prepareStatement("SELECT * FROM " + this.tableName)){
            //int res = prSt.executeUpdate();
            try(ResultSet res = prSt.executeQuery()){
                logInfo("Found data in table " + tableName);
                ArrayList<E> list = new ArrayList<>();
                while(true){
                    E entity = getEntity(res);
                    if(entity==null)
                        break;
                    list.add(entity);
                }
                return list;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logError("SQLException in database repo:" + " " + tableName + "find one method");
            return null;
        }
    }

}
