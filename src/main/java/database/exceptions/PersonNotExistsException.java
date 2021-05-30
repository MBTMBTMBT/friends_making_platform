package database.exceptions;

public class PersonNotExistsException extends RuntimeException{
    public PersonNotExistsException(String msg) {
        super(msg);
    }
}
