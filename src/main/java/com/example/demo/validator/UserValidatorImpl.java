package com.example.demo.validator;

import com.example.demo.domain.User;
import org.springframework.util.StringUtils;

public class UserValidatorImpl implements UserValidator {

    @Override
    public boolean validate(User user) {
        return !StringUtils.isEmpty(user.getFirstName()) && !StringUtils.isEmpty(user.getLastName());
    }
}
