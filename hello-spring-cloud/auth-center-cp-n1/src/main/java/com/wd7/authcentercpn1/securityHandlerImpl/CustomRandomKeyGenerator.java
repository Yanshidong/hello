package com.wd7.authcentercpn1.securityHandlerImpl;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

import java.util.UUID;

public class CustomRandomKeyGenerator implements AuthenticationKeyGenerator {
    /**
     * @param authentication an OAuth2Authentication
     * @return a unique key identifying the authentication
     */
    @Override
    public String extractKey(OAuth2Authentication authentication) {
        String token= UUID.randomUUID().toString();
        return token;
    }
}
