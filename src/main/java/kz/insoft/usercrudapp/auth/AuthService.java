package kz.insoft.usercrudapp.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;

public class AuthService {

    private static final String AUTH_TOKEN_HEADER_NAME = "MY-API-TOKEN";
    private static final String AUTH_TOKEN = "MySecret123";


    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiToken = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiToken == null || !apiToken.equals(AUTH_TOKEN)) {
            throw new BadCredentialsException("Invalid Api Token");
        }
        return new ApiKeyAuthentication(apiToken, AuthorityUtils.NO_AUTHORITIES);
    }

}
