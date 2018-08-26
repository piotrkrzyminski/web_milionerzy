package pl.milionerzy.core.repository.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.milionerzy.model.user.UserModel;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Test for {@link UserRepository}
 *
 * @author Piotr Krzyminski
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class UserRepositoryTest {

    private static final String NAME = "John";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    /**
     * Test find user by username (success)
     * Should return user object
     */
    @Test
    public void testFindUserByUsernameSuccess() {
        UserModel user = new UserModel();

        user.setUsername(NAME);
        user.setPassword("qwerty");

        entityManager.persist(user);

        UserModel result = userRepository.findByUsername(NAME);

        assertNotNull(result);
        assertEquals(NAME, result.getUsername());
    }

    /**
     * Test find user by username when it not exists
     * Should return null
     */
    @Test
    public void testFindUserByUserNameNotExists() {
        UserModel result = userRepository.findByUsername("DUMMY");

        assertNull(result);
    }
}