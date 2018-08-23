package pl.milionerzy.facades.user.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.milionerzy.core.repository.user.UserRepository;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.data.user.RegisterData;
import pl.milionerzy.model.user.UserModel;

import static org.junit.Assert.*;

/**
 * @author Piotr Krzyminski
 *
 * Test user facade functionality.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultUserFacadeIntegrationTest {

    private static final String NAME = "test";

    @Autowired
    private DefaultUserFacade userFacade;

    @Autowired
    private UserRepository userRepository;

    private RegisterData data;

    @Before
    public void init() {
        data = new RegisterData();
        data.setUsername(NAME);
        data.setPassword("qwerty");

        userRepository.deleteAll(); // clear table before test
    }

    /**
     * Test registration when user not exists in database.
     * Should add new user to database.
     */
    @Test
    public void testRegisterUserSuccess() throws UserExistsException {
        userFacade.register(data); // try to register user

        assertNotNull(userRepository.findByUsername(NAME));
    }

    /**
     * Test registration when user already exists in database.
     * Should throw exception.
     */
    @Test(expected = UserExistsException.class)
    public void testRegisterUserFailed() throws UserExistsException {
        UserModel user = new UserModel();
        user.setUsername(NAME);
        user.setPassword("qwerty");

        userRepository.save(user); // user with name 'test' exists in database

        userFacade.register(data); // try to register user which name already exists in database
    }

    /**
     * Test registration when data from form is null.
     * Should throw NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterDataIsNull() throws UserExistsException {
        userFacade.register(null);
    }

    /**
     * Test register user when name has no text.
     * Should throw exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterDataUserNameIsEmpty() throws UserExistsException {
        data.setUsername("");

        userFacade.register(data);
    }

    /**
     * Test register user when password has no text.
     * Should throw exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterDataPasswordIsEmpty() throws UserExistsException {
        data.setPassword("");

        userFacade.register(data);
    }
}