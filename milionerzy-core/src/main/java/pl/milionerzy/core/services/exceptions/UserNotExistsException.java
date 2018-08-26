package pl.milionerzy.core.services.exceptions;

/**
 * Exception thrown when user was not found in datasource.
 *
 * @author Piotr Krzyminski
 */
public class UserNotExistsException extends Exception {

    public UserNotExistsException() {
        super();
    }

    public UserNotExistsException(String message) {
        super(message);
    }
}
