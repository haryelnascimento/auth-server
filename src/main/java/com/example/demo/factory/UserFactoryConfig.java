package com.example.demo.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFactoryConfig {

    @Bean
    public GovBrUserFactory govBrUserFactory() {
        return new GovBrUserFactory();
    }
}