import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{
    public static void main(String[] args){
        ApplicationContext factory = new
              ClassPathXmlApplicationContext("classpath:beanconfig.xml");
//obtinerea referintei catre un bean din container
        //Dog d = factory.getBean(Dog.class);

        System.out.println("test");
    }
}