package com.project.networktechproject.service.auth.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFound {
    public static ResponseStatusException create(String username) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with username: %s does not exist. Create an account to proceed.", username));
    }
}
