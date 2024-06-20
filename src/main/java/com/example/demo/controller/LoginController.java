package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) auth;
            OidcUser oidcUser = (OidcUser) auth2AuthenticationToken.getPrincipal();

            model.addAttribute("username", auth2AuthenticationToken.getName());
            model.addAttribute("authorities", auth2AuthenticationToken.getAuthorities());
            model.addAttribute("token", oidcUser.getIdToken().getTokenValue());

        } else {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("authorities", userDetails.getAuthorities());
            model.addAttribute("token", "N/A");
        }

        return "home";
    }
}