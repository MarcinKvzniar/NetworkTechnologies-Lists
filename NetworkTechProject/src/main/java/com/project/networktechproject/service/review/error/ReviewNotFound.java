package com.project.networktechproject.service.review.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewNotFound {
    public static ResponseStatusException create(long id) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("Review with id: %d not found", id));
    }
}
