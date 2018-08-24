package pl.milionerzy.core.services.user;

import pl.milionerzy.core.services.exceptions.AuthenticationException;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.model.user.UserModel;

/**
 * @author Piotr Krzyminski
 */
public interface UserService {

    /**
     * Registers new user to database.
     *
     * @param userModel
     *         user to register.
     * @throws UserExistsException
     *         user cannot be registered because user with selected username already exists.
     */
    void register(UserModel userModel) throws UserExistsException;

    /**
     * Perform user authetication by checking user name and password in database.
     *
     * @param userModel user to authenticate.
     * @return user model if user will be authenticated.
     */
    UserModel authenticate(UserModel userModel) throws AuthenticationException;

}
