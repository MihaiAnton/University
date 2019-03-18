package Repository;

import Domain.Client;
import Domain.IdEntity;
import Exceptions.ValidationException;
import Validators.Validator;

public class ClientRepository<Long, Client> extends TxtRepository {


    public ClientRepository(Validator validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    protected IdEntity getEntityFromString(String line) {
        java.lang.String[] splits = line.split("\\,");
        if(splits.length !=2){
            return null;
        }
        else{
            return new Domain.Client(java.lang.Long.parseLong(splits[0]), splits[1]);
        }
    }

    @Override
    protected String getStringFromEntity(IdEntity entity) {
        Domain.Client c = (Domain.Client)entity;
        return ""+c.getId()+","+c.getName();
    }

}
