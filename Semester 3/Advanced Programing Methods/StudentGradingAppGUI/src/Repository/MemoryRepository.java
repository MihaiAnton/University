package Repository;

import Domain.IdEntity;
import Validators.Validator;

public abstract class MemoryRepository<ID, E extends IdEntity<ID>> extends GenericRepository<ID, E>{

    private String fileName;


    public MemoryRepository(Validator<E> validator, String fileName){
        super(validator);
        this.fileName = fileName;
        readFromFile();
    }


    public String getFileName(){
        return this.fileName;
    }

    public abstract void readFromFile();

    public abstract void writeToFile();

    public E save(E entity) /*throws ValidationException, IllegalArgumentException*/ {
        E retVal = super.save(entity);
        writeToFile();
        return retVal;
    }

    public E delete(ID id){
        E retVal = super.delete(id);
        writeToFile();
        return retVal;
    }

    public E update(E entity) {
        E retVal = super.update(entity);
        writeToFile();
        return retVal;
    }
}
