package com.project.networktechproject.service.user.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFound {

    public static ResponseStatusException create(long id) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with id: %d not found", id));
    }
}
