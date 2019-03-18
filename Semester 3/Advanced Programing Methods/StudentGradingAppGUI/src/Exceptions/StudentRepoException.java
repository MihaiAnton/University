package Exceptions;

public class StudentRepoException extends RuntimeException{

    /**
     *  Derived Exception class for StudentRepo tests
     */
    public StudentRepoException(String message){
        super(message);
    }

}
