package pl.milionerzy.facades.user.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pl.milionerzy.core.services.exceptions.AuthenticationException;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.core.services.user.UserService;
import pl.milionerzy.data.user.CredentialData;
import pl.milionerzy.facades.user.UserFacade;
import pl.milionerzy.model.user.UserModel;

import java.nio.file.attribute.UserPrincipalNotFoundException;

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
    public void register(CredentialData data) throws UserExistsException {

        validateData(data);

        UserModel newUser = createUserInstance(data);

        userService.register(newUser);
    }

    @Override
    public void login(String username, String password) throws AuthenticationException {

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
            throw new AuthenticationException("Username and password cannot be empty");

        userService.authenticate(createUserInstance(username, password));
    }

    private void validateData(CredentialData data) {
        notNull(data, "Register data is empty");
        Assert.hasText(data.getUsername(), "Username cannot be empty");
        Assert.hasText(data.getPassword(), "Password cannot be empty");
    }

    private UserModel createUserInstance(CredentialData data) {
        UserModel user = new UserModel();
        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());

        return user;
    }

    private UserModel createUserInstance(String username, String password) {
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);

        return user;
    }
}
