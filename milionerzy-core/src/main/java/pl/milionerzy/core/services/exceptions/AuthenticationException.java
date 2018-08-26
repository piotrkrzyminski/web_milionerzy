package pl.milionerzy.core.services.exceptions;

/**
 * Exception thrown when user cannot be authenticated.
 *
 * @author Piotr Krzyminski
 */
public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

}
