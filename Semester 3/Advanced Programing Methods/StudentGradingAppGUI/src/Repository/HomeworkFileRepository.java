package Repository;

import Domain.Homework;
import Validators.HomeworkValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class HomeworkFileRepository extends MemoryRepository<Integer, Homework> {

    public HomeworkFileRepository(HomeworkValidator validator, String file){
        super(validator, file);
    }


    @Override
    public void readFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(super.getFileName()))){

            String line;
            while((line = reader.readLine()) != null){
                String[] splits = line.split("\\|");
                if(splits.length == 5){
                    Homework homework = new Homework(Integer.parseInt(splits[0]), splits[1], Integer.parseInt(splits[2]), Integer.parseInt(splits[3]), Integer.parseInt(splits[4]));
                    save(homework);
                }
                else{
                    continue;
                }

            }
        }
        catch(Exception e){
        }
    }

    private String fileFormat(Homework h){
        return h.getId() +"|"+ h.getDescription() +"|"+ h.getTargetWeek() + "|" + h.getDeadlineWeek() + "|" + h.getAssignmentWeek();
    }

    @Override
    public void writeToFile() {


        try(PrintWriter writer = new PrintWriter(super.getFileName())){

            Consumer<Homework> write = h -> {
                writer.println(fileFormat(h));
            };

            this.getValues().stream().forEach(h -> write.accept(h));
            /*
            for(Homework h : findAll())   {
                writer.println(h.getId() +"|"+ h.getDescription() +"|"+ h.getTargetWeek() + "|" + h.getDeadlineWeek() + "|" + h.getAssignmentWeek());
            }
            */
        }
        catch (Exception e){
        }

    }
}
