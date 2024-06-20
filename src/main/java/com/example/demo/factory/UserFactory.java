package com.example.demo.factory;

import org.springframework.security.core.Authentication;

import com.example.demo.vo.UserDTO;

public interface UserFactory {
	UserDTO createUser(Authentication authentication);
}
