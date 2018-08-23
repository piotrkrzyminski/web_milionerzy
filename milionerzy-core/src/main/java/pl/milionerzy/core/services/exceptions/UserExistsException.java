package pl.milionerzy.core.services.exceptions;

/**
 * @author Piotr Krzyminski
 *
 * Exception thrown when trying to register user who's already exists in datasource.
 */
public class UserExistsException extends Exception {

    public UserExistsException() {
        super();
    }

    public UserExistsException(String message) {
        super(message);
    }

}
