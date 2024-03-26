package com.project.networktechproject.service.book.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookDetailsNotFound {

    public static ResponseStatusException create(String bookId) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Book details with id: %s not found in Google Books API", bookId));
    }
}
