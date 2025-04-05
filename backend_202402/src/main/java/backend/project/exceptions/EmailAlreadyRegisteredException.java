package backend.project.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException{
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
