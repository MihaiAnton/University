package Repository;

import Domain.IdEntity;
import Domain.Sale;
import Validators.Validator;

public class SaleRepository<Integer, Sale> extends TxtRepository {
    public SaleRepository(Validator validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    protected IdEntity getEntityFromString(String line) {
        java.lang.String[] splits = line.split("\\,");
        if(splits.length !=3){
            return null;
        }
        else{
            return new Domain.Sale(this.getValues().size(), Long.parseLong(splits[0]),splits[1], splits[2]);
        }
    }

    @Override
    protected String getStringFromEntity(IdEntity entity) {
        Domain.Sale s = (Domain.Sale)entity;
        return ""+s.getClientId()+","+s.getLaptopId()+","+s.getDate();
    }
}
