package pl.milionerzy.facades.user.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.milionerzy.core.repository.user.UserRepository;
import pl.milionerzy.core.services.exceptions.AuthenticationException;
import pl.milionerzy.core.services.exceptions.UserExistsException;
import pl.milionerzy.data.user.CredentialData;
import pl.milionerzy.data.user.UserData;
import pl.milionerzy.model.user.UserModel;

import static org.junit.Assert.*;

/**
 * Test user facade functionality.
 *
 * @author Piotr Krzyminski
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultUserFacadeIntegrationTest {

    private static final String NAME = "test";

    private static final String PASS = "qwerty";

    @Autowired
    private DefaultUserFacade userFacade;

    @Autowired
    private UserRepository userRepository;

    private CredentialData data;

    @Before
    public void init() {
        data = new CredentialData();
        data.setUsername(NAME);
        data.setPassword(PASS);

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
        user.setPassword(PASS);

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

    /**
     * Test user login ended with success.
     */
    @Test
    public void testLoginUserSuccess() throws AuthenticationException {
        UserModel user = new UserModel();
        user.setUsername(NAME);
        user.setPassword(PASS);

        userRepository.save(user);

        userFacade.login(NAME, PASS);
    }

    /**
     * Test user login when username is empty.
     * Should failed.
     */
    @Test(expected = AuthenticationException.class)
    public void testLoginEmptyUsername() throws AuthenticationException {
        userFacade.login("", PASS);
    }

    /**
     * Test user login when password is empty.
     * Should failed.
     */
    @Test(expected = AuthenticationException.class)
    public void testLoginEmptyPassowrd() throws AuthenticationException {
        userFacade.login(NAME, "");
    }

    /**
     * Test user login when password is incorrect.
     * Should failed.
     */
    @Test(expected = AuthenticationException.class)
    public void testLoginBadPassword() throws AuthenticationException {
        UserModel user = new UserModel();
        user.setUsername(NAME);
        user.setPassword(PASS);

        userRepository.save(user);

        userFacade.login(NAME, "degreg");
    }

    /**
     * Test find user by name, when exists in datasource.
     */
    @Test
    public void testGetUserByUsernameSuccess() {
        UserModel user = new UserModel();
        user.setUsername(NAME);
        user.setPassword(PASS);

        userRepository.save(user);

        UserData result = userFacade.getUserByUsername(NAME);

        assertNotNull(result);
        assertEquals(NAME, result.getUsername());
    }

    /**
     * Test find user by username when user not exists in datasource.
     * Should return null.
     */
    @Test
    public void testGetUserByUsernameNotFound() {
        UserData data = userFacade.getUserByUsername(NAME);

        assertNull(data);
    }
}