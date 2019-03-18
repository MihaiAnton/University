package AppSim;

import Domain.IdEntity;
import Repository.TxtRepository;
import Validators.Validator;

public class StudentTxtRepo<String, Student> extends TxtRepository {
    public StudentTxtRepo(Validator validator, java.lang.String fileName) {
        super(validator, fileName);
    }

    @Override
    protected IdEntity getEntityFromString(java.lang.String line) {
        java.lang.String[] splits = line.split("\\|");
        if(splits.length != 5){
            return null;
        }

        else{
            return new AppSim.Student(splits[0], splits[1],splits[2],splits[3],Integer.parseInt(splits[4]));
        }
    }

    @Override
    protected java.lang.String getStringFromEntity(IdEntity entity) {
        java.lang.String line = "";
        AppSim.Student s = (AppSim.Student)entity;
        line = line + s.getId()+"|"+s.getName()+"|"+s.getMail()+"|"+s.getTeacher()+"|"+s.getGroup();
        return line;
    }
}
