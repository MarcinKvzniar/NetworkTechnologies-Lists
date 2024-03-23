package com.project.networktechproject.service.book.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookAlreadyExists {

    public static ResponseStatusException create (String isbn) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("Book with isbn: %s already exists.", isbn));
    }
}
