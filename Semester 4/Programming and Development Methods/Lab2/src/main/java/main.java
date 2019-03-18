import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.util.Properties;

public class main {

    public static void main(String[] args) {
//        Student s = new Student(3,"bacotiu","gabi",1998,2);
//        Properties props = new Properties();
//        try{
//
//
//            props.load(new FileInputStream("bd.config"));
//        }
//        catch (Exception e){
//
//        }


        Student s = new Student(4,"avram","cosmin",1998,2);

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        DataBaseRepo dataBaseRepo = context.getBean(DataBaseRepo.class);

        Student student = context.getBean(Student.class);
        dataBaseRepo.save(student);


    }
}
