package Repository;

import Domain.Student;
import Validators.StudentValidator;

public class StudentRepository extends GenericRepository<String, Student> {

    /**
     * Extends the GenericRepository class, being specified in <StudentId, Student> pairs
     */

    public StudentRepository(StudentValidator validator){
        super(validator);
    }


}
