package com.example.demo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class GovBrAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Implementar lógica de autenticação do Gov.br aqui
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("GovBrAuthenticationProvider.supports");
        // return GovBrAuthenticationToken.class.isAssignableFrom(authentication);

        return false;
    }
}