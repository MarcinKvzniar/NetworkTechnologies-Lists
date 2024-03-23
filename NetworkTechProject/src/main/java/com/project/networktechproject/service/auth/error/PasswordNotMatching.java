package com.project.networktechproject.service.auth.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PasswordNotMatching {
    public static ResponseStatusException create () {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Incorrect password, try again");
    }
}
