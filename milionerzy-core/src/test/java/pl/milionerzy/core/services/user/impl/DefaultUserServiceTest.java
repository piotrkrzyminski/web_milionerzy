package pl.milionerzy.core.services.user.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import pl.milionerzy.core.repository.user.UserRepository;
import pl.milionerzy.core.services.exceptions.AuthenticationException;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.core.services.exceptions.UserNotExistsException;
import pl.milionerzy.model.user.UserModel;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test user service
 *
 * @author Piotr Krzyminski
 */
@RunWith(SpringRunner.class)
public class DefaultUserServiceTest {

    private static final String NAME = "Tom";

    private DefaultUserService userService;

    @Mock
    private UserRepository userRepository;

    private UserModel dummyUser;

    @Before
    public void setup() {
        userRepository = mock(UserRepository.class);
        userService = new DefaultUserService(userRepository);

        dummyUser = new UserModel();
        dummyUser.setUsername(NAME);
        dummyUser.setPassword("qwerty");
    }

    /**
     * Test user registration ended with success.
     * This should save user to database.
     */
    @Test
    public void testRegisterSuccess() throws UserExistsException {
        when(userRepository.findByUsername(NAME)).thenReturn(null); // user not exists in database

        userService.register(dummyUser);
    }

    /**
     * Test user registration while user already exists in database.
     * User should not be saved.
     */
    @Test(expected = UserExistsException.class)
    public void testRegisterFailed() throws UserExistsException {
        when(userRepository.findByUsername(NAME)).thenReturn(dummyUser); // user already exists

        userService.register(dummyUser);
    }

    /**
     * Test user authentication while user exists in database and data are proper.
     * Should be finished with success.
     */
    @Test
    public void testAuthenticateSuccess() throws AuthenticationException {
        when(userRepository.findByUsername(NAME)).thenReturn(dummyUser); // user is registered

        userService.authenticate(dummyUser);
    }

    /**
     * Test user authentication when user was not registered.
     * Should end with fail.
     */
    @Test(expected = AuthenticationException.class)
    public void testAuthenticationWhenUserNotExists() throws AuthenticationException {
        when(userRepository.findByUsername(NAME)).thenReturn(null); // user is not registered

        userService.authenticate(dummyUser);
    }

    /**
     * Test user authentication when user exists but password is incorrect.
     * Should fail.
     */
    @Test(expected = AuthenticationException.class)
    public void testAuthenticationWhenPasswordisIncorrect() throws AuthenticationException {
        when(userRepository.findByUsername(NAME)).thenReturn(dummyUser); // user is registered

        UserModel user = new UserModel();
        user.setPassword("qwe");
        user.setUsername(NAME);

        userService.authenticate(user);
    }

    /**
     * Test find user by username in datasource.
     */
    @Test
    public void testGetUserByUsernameSuccess() throws UserNotExistsException {
        when(userRepository.findByUsername(NAME)).thenReturn(dummyUser);

        UserModel result = userService.getUserByUsername(NAME);

        Assert.notNull(result);
    }

    @Test(expected = UserNotExistsException.class)
    public void testGetUserByUsernameFailed() throws UserNotExistsException {
        when(userRepository.findByUsername(any())).thenReturn(null); // user not found

        userService.getUserByUsername(NAME); // should throw exception
    }
}