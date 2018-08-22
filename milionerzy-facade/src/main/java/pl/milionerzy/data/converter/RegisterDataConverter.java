package pl.milionerzy.data.converter;

import org.springframework.core.convert.converter.Converter;
import pl.milionerzy.data.user.RegisterData;
import pl.milionerzy.model.user.UserModel;

/**
 * Converter that converts from {@link RegisterData} to {@link UserModel}.
 *
 * @author Piotr Krzyminski
 */
public class RegisterDataConverter implements Converter<UserModel, RegisterData> {

    @Override
    public RegisterData convert(UserModel model) {
        RegisterData data = new RegisterData();

        data.setUsername(model.getUsername());
        data.setPassword(model.getPassword());

        return data;
    }
}
