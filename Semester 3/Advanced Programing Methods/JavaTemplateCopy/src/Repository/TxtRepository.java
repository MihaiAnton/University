package Repository;

import Domain.IdEntity;
import Validators.Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.function.Consumer;

public abstract class TxtRepository<ID, E extends IdEntity<ID>> extends StorageRepository {


    public TxtRepository(Validator validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public void readFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(super.getFileName()))){

            String line;
            while((line = reader.readLine()) != null){
                //String[] splits = line.split("\\|");

                if(line.length() > 0){
                    //Student student = new Student(splits[0],splits[1],Integer.parseInt(splits[2]),splits[3],splits[4]);
                    E entity = getEntityFromString(line);

                    save(entity);
                }
                else{
                    continue;
                }
            }
        }
        catch(Exception e){
        }
    }

    protected abstract E getEntityFromString(String line);


    @Override
    public void writeToFile() {

        try(PrintWriter writer = new PrintWriter(super.getFileName())){
            Consumer<E> write = entity -> writer.println(getStringFromEntity(entity));

            this.getValues().stream().forEach(s -> write.accept((E)s));

            /*
            for(Student s : findAll()){
                writer.println(s.getId() + "|" + s.getName() + "|" + s.getGroup() + "|" + s.getEmail() + "|" + s.getTeacher());
            }
            */
        }
        catch(Exception e){
        }
    }

    protected abstract String getStringFromEntity(E entity);
}






























