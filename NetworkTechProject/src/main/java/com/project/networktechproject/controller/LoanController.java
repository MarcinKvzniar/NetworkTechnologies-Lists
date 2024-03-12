package com.project.networktechproject.controller;

import com.project.networktechproject.infrastructure.dto.LoanDTO;
import com.project.networktechproject.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping(path = "/add")
    public String addNewLoan(@RequestBody LoanDTO loanDTO) {
        loanService.saveLoan(loanDTO);
        return "Saved";
    }

    @GetMapping
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }
}

