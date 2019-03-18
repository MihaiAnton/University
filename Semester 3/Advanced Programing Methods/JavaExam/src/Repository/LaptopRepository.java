package Repository;

import Domain.IdEntity;
import Domain.LaptopType;
import Validators.Validator;

public class LaptopRepository<String, Laptop> extends TxtRepository{
    public LaptopRepository(Validator validator, java.lang.String fileName) {
        super(validator, fileName);
    }

    @Override
    protected IdEntity getEntityFromString(java.lang.String line) {
        java.lang.String[] splits = line.split("\\,");
        if(splits.length !=5){
            return null;
        }
        else{
            return new Domain.Laptop(splits[0], splits[1], splits[2],Float.parseFloat(splits[4]), splits[3]);
        }
    }

    @Override
    protected java.lang.String getStringFromEntity(IdEntity entity) {
        Domain.Laptop l = (Domain.Laptop)entity;
        return ""+l.getId()+","+l.getProd()+","+l.getModel()+","+l.getType()+","+l.getPrice();
    }
}
