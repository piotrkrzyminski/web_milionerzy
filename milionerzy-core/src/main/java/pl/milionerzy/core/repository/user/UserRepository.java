package pl.milionerzy.core.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.milionerzy.model.user.UserModel;

/**
 * Repository for {@link UserModel}.
 *
 * @author Piotr Krzyminski
 */
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);
}
