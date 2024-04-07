package com.project.networktechproject.service.loan.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanAlreadyReturned {
    public static ResponseStatusException create (long id) {
        return new ResponseStatusException(HttpStatus.CONFLICT,
                String.format("Loan with ID: %s has already been returned.", id));
    }
}
