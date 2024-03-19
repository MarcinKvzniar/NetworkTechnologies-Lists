package com.project.networktechproject.service;

import com.project.networktechproject.controller.dto.loan.LoanDto;
import com.project.networktechproject.infrastructure.entity.LoanEntity;
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

    public void saveLoan(LoanDto loanDTO) {
        LoanEntity loanEntity = new LoanEntity();

        loanEntity.setBookId(loanDTO.getBookId());
        loanEntity.setUserId(loanDTO.getUserId());
        loanEntity.setLoanDate(loanDTO.getLoanDate());
        loanEntity.setDueDate(loanDTO.getDueDate());
        loanEntity.setReturnDate(loanDTO.getReturnDate());
        loanRepository.save(loanEntity);
    }

    public List<LoanDto> getAllLoans() {
        List<LoanEntity> loanEntities = loanRepository.findAll();
        return loanEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private LoanDto convertToDTO(LoanEntity loanEntity) {
        LoanDto loanDTO = new LoanDto();

        loanDTO.setBookId(loanEntity.getBookId());
        loanDTO.setLoanDate(loanEntity.getLoanDate());
        loanDTO.setDueDate(loanEntity.getDueDate());
        loanDTO.setReturnDate(loanEntity.getReturnDate());
        return loanDTO;
    }

}
