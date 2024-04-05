package com.project.networktechproject.service.auth.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExists {

    public static ResponseStatusException createWithUsername(String username) {
        return new ResponseStatusException(HttpStatus.CONFLICT,
                String.format("User with username: %s already exists.", username));
    }

    public static ResponseStatusException createWithEmail(String email) {
        return new ResponseStatusException(HttpStatus.CONFLICT,
                String.format("User with email: %s already exists.", email));
    }
}
