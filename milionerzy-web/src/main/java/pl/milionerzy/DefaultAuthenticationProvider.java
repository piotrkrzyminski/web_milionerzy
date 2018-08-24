package pl.milionerzy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pl.milionerzy.facades.user.UserFacade;

import java.util.Collections;

/**
 * @author Piotr Krzyminski
 *
 * Default authetication provider for login.
 */
@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAuthenticationProvider.class);

    private final UserFacade userFacade;

    @Autowired
    public DefaultAuthenticationProvider(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    public Authentication authenticate(
            Authentication authentication) throws AuthenticationException {

        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        try {
            userFacade.login(username, password);
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            throw new BadCredentialsException(e.getMessage());
        }

        return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
