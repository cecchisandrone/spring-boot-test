package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.validator.UserValidator;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CommonsLog
@RunWith(SpringRunner.class)
@WebMvcTest(RestController.class)
public class UserValidatorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserValidator userValidator;

    @Before
    public void setUp() {

        log.info("Beans");
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            log.info("--->>> " + beanDefinitionName);
        }
    }

    @Test
    public void testUserValidator() {
        assertFalse(userValidator.validate(new User()));
    }

    @TestConfiguration
    static class AnotherRestControllerTestConfig {

        @Bean
        UserValidator userValidatorOther() {
            return (user) -> false;
        }
    }
}