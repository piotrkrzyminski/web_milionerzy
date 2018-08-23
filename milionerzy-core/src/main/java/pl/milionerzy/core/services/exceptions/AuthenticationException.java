package pl.milionerzy.core.services.exceptions;

/**
 * @author Piotr Krzyminski
 *
 * Exception thrown when user cannot be authentificated.
 */
public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

}
