package com.example.demo;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.oauth2.OAuth2AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private GovBrAuthenticationProvider govBrAuthenticationProvider;

  @Autowired
  private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(
            authorizeConfig -> {
              authorizeConfig.requestMatchers("/login").permitAll();
              authorizeConfig.requestMatchers("/logout").permitAll();
              authorizeConfig.anyRequest().authenticated();
            })
        .formLogin(formLogin -> formLogin.loginPage("/login")
            .defaultSuccessUrl("/home", true)
            .permitAll()
            .successHandler(oAuth2AuthenticationSuccessHandler))
        .oauth2Login(oauth2Login -> oauth2Login
            .loginPage("/login")
            .defaultSuccessUrl("/home", true)
            .successHandler(oAuth2AuthenticationSuccessHandler))
        .oauth2ResourceServer(conf -> conf.jwt(withDefaults()))
        .logout(logout -> logout.logoutSuccessUrl("/login").permitAll())
        // .addFilterBefore(tokenAuthenticationFilter(),
        // UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http
        .getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
    authenticationManagerBuilder.authenticationProvider(govBrAuthenticationProvider);

    return authenticationManagerBuilder.build();
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}