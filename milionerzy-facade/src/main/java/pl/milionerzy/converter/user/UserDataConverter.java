package pl.milionerzy.converter.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.milionerzy.data.user.UserData;
import pl.milionerzy.model.user.UserModel;

/**
 * Converter that converts {@link UserModel} to {@link UserData}.
 *
 * @author Piotr Krzyminski
 */
@Component
public class UserDataConverter implements Converter<UserModel, UserData> {

    @Override
    public UserData convert(UserModel userModel) {
        UserData data = new UserData();

        data.setUsername(userModel.getUsername());

        return data;
    }
}
