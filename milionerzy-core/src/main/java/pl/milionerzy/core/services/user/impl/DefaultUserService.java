package pl.milionerzy.core.services.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.milionerzy.core.repository.user.UserRepository;
import pl.milionerzy.core.services.exceptions.AuthenticationException;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.core.services.exceptions.UserNotExistsException;
import pl.milionerzy.core.services.user.UserService;
import pl.milionerzy.model.user.UserModel;

/**
 * Default implementation of {@link UserService}.
 *
 * @author Piotr Krzyminski
 */
@Service
public class DefaultUserService implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);

    private UserRepository userRepository;

    @Autowired
    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserModel userModel) throws UserExistsException {
        LOG.debug("Checking if user with name " + userModel.getUsername() + " already exists in datasource");

        if (isUserExistsInDatasource(userModel.getUsername())) {
            throw new UserExistsException("User with this name already exists");
        }

        userRepository.save(userModel);
    }

    @Override
    public UserModel authenticate(UserModel userModel) throws AuthenticationException {
        LOG.debug("Performing user authentication");

        UserModel user = userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            if (user.getPassword().equals(userModel.getPassword())) {
                LOG.debug("User successfully authenticated");
                return user;
            }
        }

        throw new AuthenticationException("Bad credentials");
    }

    @Override
    public UserModel getUserByUsername(String username) throws UserNotExistsException {

        LOG.debug("Searching for user with username " + username);

        UserModel user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotExistsException("User with name " + username + " was not found");
        }

        return user;
    }

    private boolean isUserExistsInDatasource(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
