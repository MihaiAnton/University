package Repository;

import Domain.IdEntity;
import Validators.Validator;

public abstract class StorageRepository<ID, E extends IdEntity<ID>> extends GenericHashMapRepository<ID, E>{

    protected String fileName;
    protected String entity;


    public StorageRepository(Validator<E> validator, String fileName,String entity){
        super(validator);
        this.fileName = fileName;
        this.entity = entity;
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
