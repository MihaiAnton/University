import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseRepo implements CRUDRepo<Integer, Student>{

    private Properties jdbcProps;
    Connection instance = null;

    public DataBaseRepo(Properties props) {
        jdbcProps = props;
        instance = getNewConnection();
    }


    private Connection getNewConnection(){

        String driver = jdbcProps.getProperty("tasks.jdbc.driver");
        String url = jdbcProps.getProperty("tasks.jdbc.url");
        String user = jdbcProps.getProperty("tasks.jdbc.user");
        String pass = jdbcProps.getProperty("tasks.jdbc.pass");

        Connection con = null;
        try{
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {

            System.out.println("Error loading driver " + e);
        } catch (SQLException e) {

            System.out.printf("Error getting connection " + e);
        }
        return con;
    }

    @Override
    public Student save(Student entity) {

        try {

            String statement = "insert into students values (" + entity.getSurname() + "," + entity.getYearOfBirth() + "," + entity.getName() + ")";
            PreparedStatement preparedStatement = instance.prepareStatement(statement);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){

        }

        try(PreparedStatement prSt = instance.prepareStatement("insert into students(surname, yearOfBirth, sname) values (?, ?, ?)")) {

            prSt.setString(1, entity.getSurname());
            prSt.setInt(2, entity.getYearOfBirth());
            prSt.setString(3, entity.getName());
            int  res = prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student update(Student entity) {
        return null;
    }

    @Override
    public Student delete(Student entity) {
        return null;
    }

    @Override
    public Student findOne(Integer integer) {
        return null;
    }
}
