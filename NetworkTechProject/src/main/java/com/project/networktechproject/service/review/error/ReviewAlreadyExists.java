package com.project.networktechproject.service.review.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewAlreadyExists {
    public static ResponseStatusException create (long bookId, long userId) {
        return new ResponseStatusException(
                HttpStatus.CONFLICT,
                String.format("Review with user ID: %s and book ID: %s already exists.", userId, bookId)
        );
    }
}
