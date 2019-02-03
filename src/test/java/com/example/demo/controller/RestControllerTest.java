package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.validator.UserValidator;
import com.example.demo.validator.UserValidatorImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CommonsLog
@RunWith(SpringRunner.class)
@WebMvcTest(RestController.class)
public class RestControllerTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {

        log.info("Beans");
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            log.info("--->>> " + beanDefinitionName);
        }
    }

    @Test
    public void getUsers() throws Exception {

        given(userRepository.findAll()).willReturn(new ArrayList<>());

        mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void createUser() throws Exception {

        User user = new User();
        user.setFirstName("Alessandro");
        user.setLastName("Dionisi");
        user.setId(1L);
        given(userRepository.save(any(User.class))).willReturn(user);

        mockMvc.perform(post("/users").content("{\"firstName\":\"Alessandro\",\"lastName\":\"Dionisi\"}").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"firstName\":\"Alessandro\",\"lastName\":\"Dionisi\"}"));
    }

    @TestConfiguration
    static class RestControllerTestConfig {

        @Bean
        UserValidator userValidator() {
            return (user) -> true;
        }
    }
}