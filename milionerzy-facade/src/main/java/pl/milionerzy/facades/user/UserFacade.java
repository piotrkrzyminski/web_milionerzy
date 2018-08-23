package pl.milionerzy.facades.user;

import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.data.user.RegisterData;

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
    void register(RegisterData data) throws UserExistsException;
}
