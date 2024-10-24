package com.gastos.seguimiento.controller.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class TokenAuthentication extends AbstractAuthenticationToken {

    private final String token;
    private final String email;  // Usamos email como principal

    public TokenAuthentication(String token, String email) {
        super(null);
        this.token = token;
        this.email = email;
    }

    @Override
    public boolean isAuthenticated() {
        return token != null && !token.isEmpty() && email != null && !email.isEmpty();
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return email;  // Aquí retornamos el email como el principal
    }
}
