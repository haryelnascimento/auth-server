package com.example.demo.factory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.example.demo.vo.UserDTO;

public class UserFactoryManager {

    private final Map<String, UserFactory> userFactories;

    @Autowired
    public UserFactoryManager(ApplicationContext context) {
        this.userFactories = context.getBeansOfType(UserFactory.class);
    }

    public UserDTO createUser(Authentication authentication) {
        String provider = extractProvider((OAuth2AuthenticationToken) authentication);
        return userFactories.get(provider).createUser(authentication);
    }

    private String extractProvider(OAuth2AuthenticationToken authentication) {
        return authentication.getAuthorizedClientRegistrationId();
    }

}
