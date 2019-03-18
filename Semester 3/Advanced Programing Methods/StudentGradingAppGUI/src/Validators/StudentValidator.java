package Validators;

import Domain.Student;
import Exceptions.ValidationException;

/**
 *  Validator class specialized on Students
 */
public class StudentValidator implements Validator<Student> {

    /**
     *
     * @param s
     * @throws ValidationException
     */
    public void validate(Student s) /*throws ValidationException*/{
       String error = "";

       try{
           validateId(s.getId());
       }
       catch (Exception e){
           error = error + e.getMessage() + "\n";
       }

       try{
           validateGroup(s.getGroup());
       }
       catch (Exception e){
           error = error + e.getMessage() + "\n";
       }

       if(error.length() > 0){
           throw new ValidationException(error);
       }
    }

    /**
     *
     * @param id
     * @throws ValidationException
     */
    private void validateId(String id){  /*throws ValidationException*/
        boolean hasChar = false;
        for(int i=0;i<id.length();i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9')
                hasChar = true;
        }

        if(id.length() !=4  || id.charAt(0) == '0'){
            hasChar = true;
        }

        if(hasChar)
            throw new ValidationException("Incorrect student id.");
    }

    /**
     *
     * @param id
     * @throws ValidationException
     */
    private void validateGroup(int id)  /*throws ValidationException*/{
        if(id < 111 || id > 999){
            throw new ValidationException("Incorrect student group.");
        }
    }

}
