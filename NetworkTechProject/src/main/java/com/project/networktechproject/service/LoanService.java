package com.project.networktechproject.service;

import com.project.networktechproject.infrastructure.entity.LoanEntity;
import com.project.networktechproject.infrastructure.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<LoanEntity> getAll() {
        return loanRepository.findAll();
    }
}
