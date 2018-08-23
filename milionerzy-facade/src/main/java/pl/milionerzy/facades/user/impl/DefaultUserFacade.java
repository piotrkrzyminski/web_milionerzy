package pl.milionerzy.facades.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.core.services.user.UserService;
import pl.milionerzy.data.user.RegisterData;
import pl.milionerzy.facades.user.UserFacade;
import pl.milionerzy.model.user.UserModel;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Default implementation of {@link UserFacade}
 *
 * @author Piotr Krzyminski
 */
@Component
public class DefaultUserFacade implements UserFacade {

    private UserService userService;

    @Autowired
    public DefaultUserFacade(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void register(RegisterData data) throws UserExistsException {

        notNull(data, "Register data is empty");
        Assert.hasText(data.getUsername(), "Username cannot be empty");
        Assert.hasText(data.getPassword(), "Password cannot be empty");

        UserModel newUser = new UserModel();
        newUser.setUsername(data.getUsername());
        newUser.setPassword(data.getPassword());

        userService.register(newUser);
    }
}
