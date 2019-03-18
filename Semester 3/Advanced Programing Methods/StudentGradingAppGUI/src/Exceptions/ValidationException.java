package Exceptions;

public class ValidationException extends RuntimeException {

    /**
     *  Derived Exception class for Validations
     */
    public ValidationException(String message){
        super(message);
    }
}
