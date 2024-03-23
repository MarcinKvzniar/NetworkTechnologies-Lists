package com.project.networktechproject.service.book.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFound {
    public static ResponseStatusException create(long id) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("Book with id: %d not found", id));
    }
}
