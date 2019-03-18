package Repository;

import Domain.Homework;
import Validators.HomeworkValidator;

public class Register extends GenericRepository<Integer, Homework>{

    /**
     * Extends the GenericRepository class, being specified in <HomeworkId, Homework> pairs
     */


    public Register(HomeworkValidator validator) {
        super(validator);
    }
}
