package pl.milionerzy.core.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.milionerzy.model.user.UserModel;

/**
 * @author Piotr Krzyminski
 *
 * Repository for {@link UserModel}.
 */
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

}
