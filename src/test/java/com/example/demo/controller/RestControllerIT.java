package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@CommonsLog
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestControllerIT {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void printBeans() {
        System.out.println("Integration test beans");
        log.info("Beans");
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            log.info("--->>> " + beanDefinitionName);
        }
    }

    @Test
    public void saveEntity() {
        User user = new User();
        user.setFirstName("Alessandro");
        user.setLastName("Dionisi");
        userRepository.save(user);
    }

    @EntityScan(basePackages = "com.example.demo.domain")
    @EnableJpaRepositories(basePackages = "com.example.demo.repository")
    @TestConfiguration
    static class RestControllerITConfig {

    }
}
