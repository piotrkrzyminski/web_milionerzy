package pl.milionerzy.data.facades.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.core.services.user.UserService;
import pl.milionerzy.data.facades.user.UserFacade;
import pl.milionerzy.data.user.RegisterData;
import pl.milionerzy.model.user.UserModel;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Default implementation of {@link UserFacade}
 *
 * @author Piotr Krzyminski
 */
@Component
public class DefaultUserFacade implements UserFacade {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserFacade.class);

    private UserService userService;

    @Autowired
    public DefaultUserFacade(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void register(RegisterData data) throws UserExistsException {

        notNull(data.getUsername(), "Username cannot be null");
        notNull(data.getPassword(), "Password cannot be null");

        UserModel user = new UserModel();
        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());

        userService.register(user);
    }
}
