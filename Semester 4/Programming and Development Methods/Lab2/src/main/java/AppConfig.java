import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    @Primary
    public Properties jdbcProps(){
        Properties properties = new Properties();
        try{
            properties.load(getClass().getResourceAsStream("bd.config"));
            properties.list(System.out);
        }
        catch (Exception e){
            System.out.println("Error");
        }
        return properties;
    }


    @Bean(name = "Address")
    public Address createAddress(){
        return new Address("testStreet",0);
    }

    @Bean(name ="Student")
    public Student createStudent(){
        return new Student(1,"test","test",2018,2,createAddress());
    }

    @Bean(name = "DataBaseRepo")
    public DataBaseRepo createRepo(Properties properties){
        return new DataBaseRepo(properties);
    }
}
