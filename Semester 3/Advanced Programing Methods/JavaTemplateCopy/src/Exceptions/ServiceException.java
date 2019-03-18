package Exceptions;

public class ServiceException extends RuntimeException {
    public ServiceException(String message){
        super(message);
    }
}
