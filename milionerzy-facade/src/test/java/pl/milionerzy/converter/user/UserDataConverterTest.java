package pl.milionerzy.converter.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.milionerzy.data.user.UserData;
import pl.milionerzy.model.user.UserModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test for user conversion.
 *
 * @author Piotr Krzyminski
 */
@RunWith(SpringRunner.class)
public class UserDataConverterTest {

    private static final String NAME = "dummy";

    private UserDataConverter converter;

    private UserModel source;

    @Before
    public void setup() {
        converter = new UserDataConverter();

        source = new UserModel();
        source.setUsername(NAME);
        source.setPassword("qwerty");
    }

    /**
     * Test conversion from model to dto.
     */
    @Test
    public void testUserDataConversion() {
        UserData data = converter.convert(source);

        assertNotNull(data);
        assertEquals(NAME, data.getUsername());
    }
}