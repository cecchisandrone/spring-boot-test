package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.validator.UserValidator;
import com.example.demo.validator.UserValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/users")
    public HttpEntity<User> createUser(@RequestBody User user) {

        if (userValidator.validate(user)) {
            return new HttpEntity<>(userRepository.save(user));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
