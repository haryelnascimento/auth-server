package com.example.demo;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import com.example.demo.model.user.User;

public class AuthUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public AuthUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
