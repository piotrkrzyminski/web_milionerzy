package pl.milionerzy.core.services.user.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import pl.milionerzy.core.repository.user.UserRepository;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.model.user.UserModel;

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
}