package com.project.networktechproject.service.review.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewRatingNotValid {
    public static ResponseStatusException create (int rating) {
        return new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("Rating %s is not valid. Rating must be between 1 and 10.", rating)
        );
    }
}

