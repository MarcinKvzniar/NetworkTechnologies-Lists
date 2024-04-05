package com.project.networktechproject.service.review.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewNotFound {
    public static ResponseStatusException createWithId(long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Review with id: %d not found", id));
    }

    public static ResponseStatusException createWithBookIdAndUserId(long bookId, long userId) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %s have not borrowed book with id %s." +
                        " You has to borrow the book in order to review it.", userId, bookId));
    }
}
