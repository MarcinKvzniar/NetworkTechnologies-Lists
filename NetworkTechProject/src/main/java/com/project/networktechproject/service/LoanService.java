package com.project.networktechproject.service;

import com.project.networktechproject.controller.dto.loan.GetLoanDto;
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

    public void saveLoan(GetLoanDto getLoanDTO) {
        LoanEntity loanEntity = new LoanEntity();

        loanEntity.setBookId(getLoanDTO.getBookId());
        loanEntity.setUserId(getLoanDTO.getUserId());
        loanEntity.setLoanDate(getLoanDTO.getLoanDate());
        loanEntity.setDueDate(getLoanDTO.getDueDate());
        loanEntity.setReturnDate(getLoanDTO.getReturnDate());
        loanRepository.save(loanEntity);
    }

    public List<GetLoanDto> getAllLoans() {
        List<LoanEntity> loanEntities = loanRepository.findAll();
        return loanEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GetLoanDto convertToDTO(LoanEntity loanEntity) {
        GetLoanDto getLoanDTO = new GetLoanDto();

        getLoanDTO.setBookId(loanEntity.getBookId());
        getLoanDTO.setLoanDate(loanEntity.getLoanDate());
        getLoanDTO.setDueDate(loanEntity.getDueDate());
        getLoanDTO.setReturnDate(loanEntity.getReturnDate());
        return getLoanDTO;
    }

}
