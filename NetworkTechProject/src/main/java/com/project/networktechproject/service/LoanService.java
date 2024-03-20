package com.project.networktechproject.service;

import com.project.networktechproject.controller.dto.book.GetBookDto;
import com.project.networktechproject.controller.dto.loan.GetLoanDto;
import com.project.networktechproject.infrastructure.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<GetLoanDto> getAll() {
        var loans = loanRepository.findAll();

        return loans
                .stream()
                .map(loan -> new GetLoanDto(
                    loan.getId(),
                    loan.getBookId(),
                    loan.getUserId(),
                    loan.getLoanDate(),
                    loan.getDueDate(),
                    loan.getReturnDate()
                ))
                .collect(Collectors.toList());
    }

}
