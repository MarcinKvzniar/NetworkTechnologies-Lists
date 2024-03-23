package com.project.networktechproject.service.loan.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanNotFound {
    public static ResponseStatusException create(long id) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("Loan with id: %d not found", id));
    }
}
