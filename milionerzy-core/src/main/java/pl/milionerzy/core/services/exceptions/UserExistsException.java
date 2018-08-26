package pl.milionerzy.core.services.exceptions;

/**
 * Exception thrown when trying to register user who's already exists in datasource.
 *
 * @author Piotr Krzyminski
 */
public class UserExistsException extends Exception {

    public UserExistsException() {
        super();
    }

    public UserExistsException(String message) {
        super(message);
    }

}
