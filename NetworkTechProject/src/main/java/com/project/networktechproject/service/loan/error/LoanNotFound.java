package com.project.networktechproject.service.loan.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanNotFound {
    public static ResponseStatusException createWithId(long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Loan with id: %s not found", id));
    }

    public static ResponseStatusException createWithBookIdAndUserId(long bookId, long userId) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Loan with bookId: %s and userId: %s not found", bookId, userId));
    }
}
