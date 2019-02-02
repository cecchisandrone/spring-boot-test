package com.example.demo.config;

import com.example.demo.validator.UserValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Bean
    public UserValidatorImpl userValidator() {
        return new UserValidatorImpl();
    }
}
