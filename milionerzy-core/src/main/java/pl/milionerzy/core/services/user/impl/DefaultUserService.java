package pl.milionerzy.core.services.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.milionerzy.core.repository.user.UserRepository;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.core.services.user.UserService;
import pl.milionerzy.model.user.UserModel;

/**
 * @author Piotr Krzyminski
 *
 * Default implementation of {@link UserService}.
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
            userRepository.save(userModel);
        }

        throw new UserExistsException("User with this name already exists");
    }

    private boolean isUserExistsInDatasource(String username) {
        return userRepository.findByUsername(username) == null ? false : true;
    }
}
