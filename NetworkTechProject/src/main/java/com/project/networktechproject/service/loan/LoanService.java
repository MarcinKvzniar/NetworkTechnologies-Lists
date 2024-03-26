package com.project.networktechproject.service.loan;

import com.project.networktechproject.controller.book.dto.GetBookDto;
import com.project.networktechproject.controller.loan.dto.CreateLoanDto;
import com.project.networktechproject.controller.loan.dto.CreateLoanResponseDto;
import com.project.networktechproject.controller.loan.dto.GetLoanResponseDto;
import com.project.networktechproject.controller.user.dto.GetUserDto;
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

import java.sql.Date;
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

    public GetLoanResponseDto getOneById(long id) {
        LoanEntity loan = loanRepository
                .findById(id)
                .orElseThrow(() -> LoanNotFound.create(id));

        return mapLoan(loan);
    }

    public List<GetLoanResponseDto> getAll() {
        List<LoanEntity> loans = loanRepository.findAll();

        return loans
                .stream()
                .map(this::mapLoan)
                .collect(Collectors.toList());
    }

    public CreateLoanResponseDto create(CreateLoanDto loanDto) {
        Optional<LoanEntity> existingLoan = loanRepository
                .findByBookIdAndUserIdAndReturnDateIsNull(loanDto.getBookId(), loanDto.getUserId());

        if (existingLoan.isPresent()) {
            throw LoanAlreadyExists.create(loanDto.getBookId(), loanDto.getUserId());
        }

        var book = bookRepository
                .findById(loanDto.getBookId())
                .orElseThrow(() -> BookNotFound.create(loanDto.getBookId()));

        var user = userRepository
                .findById(loanDto.getUserId())
                .orElseThrow(() -> UserNotFound.create(loanDto.getUserId()));

        LoanEntity loan = new LoanEntity();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(new Date(System.currentTimeMillis()));
        loan.setDueDate(loanDto.getDueDate());
        loanRepository.save(loan);

        return new CreateLoanResponseDto(
                loan.getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getUser().getId(),
                loan.getBook().getId()
        );
    }

    public void delete(long id) {
        if (!loanRepository.existsById(id)) {
            throw LoanNotFound.create(id);
        }
        loanRepository.deleteById(id);
    }

    private GetLoanResponseDto mapLoan(LoanEntity loan) {
        GetUserDto user = new GetUserDto(
                loan.getUser().getId(),
                loan.getUser().getName(),
                loan.getUser().getLastName(),
                loan.getUser().getDateOfBirth(),
                loan.getUser().getEmail()
        );

        GetBookDto book = new GetBookDto(
                loan.getBook().getId(),
                loan.getBook().getIsbn(),
                loan.getBook().getTitle(),
                loan.getBook().getAuthor(),
                loan.getBook().getPublisher(),
                loan.getBook().getYearPublished(),
                loan.getBook().getAvailableCopies() > 0
        );

        return new GetLoanResponseDto(
                loan.getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                user,
                book
        );
    }

}
