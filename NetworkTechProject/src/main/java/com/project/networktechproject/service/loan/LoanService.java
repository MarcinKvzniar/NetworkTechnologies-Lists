package com.project.networktechproject.service.loan;

import com.project.networktechproject.controller.loan.dto.CreateLoanDto;
import com.project.networktechproject.controller.loan.dto.CreateLoanResponseDto;
import com.project.networktechproject.controller.loan.dto.GetLoanDto;
import com.project.networktechproject.infrastructure.entity.LoanEntity;
import com.project.networktechproject.infrastructure.repository.BookRepository;
import com.project.networktechproject.infrastructure.repository.LoanRepository;
import com.project.networktechproject.infrastructure.repository.UserRepository;
import com.project.networktechproject.service.book.error.BookNotFound;
import com.project.networktechproject.service.loan.error.LoanAlreadyExists;
import com.project.networktechproject.service.loan.error.LoanNotFound;
import com.project.networktechproject.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
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

    public GetLoanDto getOne(long id) {
        var loan = loanRepository
                .findById(id)
                .orElseThrow(() -> LoanNotFound.create(id));

        return new GetLoanDto(
                loan.getId(),
                loan.getBookId(),
                loan.getUserId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate()
        );
    }

    public CreateLoanResponseDto create(CreateLoanDto loan) {
        Optional<LoanEntity> existingLoan = loanRepository.findByBookIdAndUserIdAndReturnDateIsNull(loan.getBookId(), loan.getUserId());

        if (existingLoan.isPresent()) {
            throw LoanAlreadyExists.create(loan.getBookId().getId(), loan.getUserId().getId());
        }

        var loanEntity = new LoanEntity();

        var bookEntity = bookRepository
                .findById(loan.getBookId().getId())
                .orElseThrow(() -> BookNotFound.create(loan.getBookId().getId()));

        var userEntity = userRepository
                .findById(loan.getUserId().getId())
                .orElseThrow(() -> UserNotFound.create(loan.getUserId().getId()));

        loanEntity.setBookId(bookEntity);
        loanEntity.setUserId(userEntity);
        loanEntity.setLoanDate(loan.getLoanDate());
        loanEntity.setDueDate(loan.getDueDate());
        loanEntity.setReturnDate(loan.getReturnDate());

        var newLoan = loanRepository.save(loanEntity);

        return new CreateLoanResponseDto(
                newLoan.getId(),
                newLoan.getBookId(),
                newLoan.getUserId(),
                newLoan.getLoanDate(),
                newLoan.getDueDate(),
                newLoan.getReturnDate()
        );
    }

    public void delete(long id) {
        if (!loanRepository.existsById(id)) {
            throw LoanNotFound.create(id);
        }
        loanRepository.deleteById(id);
    }

}
