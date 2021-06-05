package database.exceptions;

public class WrongAttributeNameException extends RuntimeException {
    public WrongAttributeNameException(String msg) {
        super(msg);
    }
}
