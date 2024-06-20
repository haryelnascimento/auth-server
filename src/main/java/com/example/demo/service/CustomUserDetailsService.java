package com.example.demo.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.AuthUserDetails;
import com.example.demo.model.user.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = new User(1L, "username", "$2a$12$tZBHeEDagDa8xa7gULWAOeL.acfUixWGnfQyDQkU0kfXt/N3cg66K", "haryelfh@gmail.com");

        // TODO: Implement your own logic to get user from the database

        return new AuthUserDetails(user, getPermissions(user));
    }

    public Collection<? extends GrantedAuthority> getPermissions(User user) {
        Set<String> permissions = new HashSet<>();
        permissions.add("ROLE_USER");

        return permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}