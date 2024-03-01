package kz.insoft.usercrudapp.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ApiKeyAuthentication extends AbstractAuthenticationToken {

    private String apiToken;

    public ApiKeyAuthentication(String apiToken, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiToken = apiToken;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiToken;
    }
}
