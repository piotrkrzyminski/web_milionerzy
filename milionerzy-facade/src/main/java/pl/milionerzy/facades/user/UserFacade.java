package pl.milionerzy.facades.user;

import pl.milionerzy.core.services.exceptions.AuthenticationException;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.data.user.CredentialData;
import pl.milionerzy.model.user.UserModel;

import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 * @author Piotr Krzyminski
 */
public interface UserFacade {

    /**
     * Perform user registration.
     *
     * @param data user data from web form.
     * @throws UserExistsException cannot register user that already exists.
     */
    void register(CredentialData data) throws UserExistsException;

    /**
     * Perform user authentication.
     *
     * @param username user name.
     * @param password user password.
     * @throws AuthenticationException user cannot be authenticated.
     */
    void login(String username, String password) throws AuthenticationException;
}
