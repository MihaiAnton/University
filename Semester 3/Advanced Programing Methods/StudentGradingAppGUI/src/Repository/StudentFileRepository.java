package Repository;

import Domain.IdEntity;
import Domain.Student;
import Validators.StudentValidator;
import Validators.Validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class StudentFileRepository extends MemoryRepository<String, Student>{


    public StudentFileRepository(StudentValidator validator, String fileName){
        super(validator, fileName);
    }

    @Override
    public void readFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(super.getFileName()))){

            String line;
            while((line = reader.readLine()) != null){
                String[] splits = line.split("\\|");

                if(splits.length == 5){
                    Student student = new Student(splits[0],splits[1],Integer.parseInt(splits[2]),splits[3],splits[4]);
                    save(student);
                }
                else{
                    continue;
                }
            }
        }
        catch(Exception e){
        }
    }

    private String fileFormat(Student s){
        return s.getId() + "|" + s.getName() + "|" + s.getGroup() + "|" + s.getEmail() + "|" + s.getTeacher();
    }

    @Override
    public void writeToFile(){

        try(PrintWriter writer = new PrintWriter(super.getFileName())){
            Consumer<Student> write = s -> writer.println(fileFormat(s));

            this.getValues().stream().forEach(s -> write.accept(s));

            /*
            for(Student s : findAll()){
                writer.println(s.getId() + "|" + s.getName() + "|" + s.getGroup() + "|" + s.getEmail() + "|" + s.getTeacher());
            }
            */
        }
        catch(Exception e){
        }
    }

    public Student save(Student student){
        Student rez = super.save(student);
        return rez;
    }
}
