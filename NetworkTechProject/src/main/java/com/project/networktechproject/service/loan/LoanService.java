package com.project.networktechproject.service.loan;

import com.project.networktechproject.controller.book.dto.GetBookResponseDto;
import com.project.networktechproject.controller.loan.dto.*;
import com.project.networktechproject.controller.user.dto.GetUserDto;
import com.project.networktechproject.infrastructure.entity.LoanEntity;
import com.project.networktechproject.infrastructure.repository.AuthRepository;
import com.project.networktechproject.infrastructure.repository.BookRepository;
import com.project.networktechproject.infrastructure.repository.LoanRepository;
import com.project.networktechproject.infrastructure.repository.UserRepository;
import com.project.networktechproject.service.auth.OwnershipService;
import com.project.networktechproject.service.auth.error.Forbidden;
import com.project.networktechproject.service.book.error.BookNotFound;
import com.project.networktechproject.service.loan.error.LoanAlreadyExists;
import com.project.networktechproject.service.loan.error.LoanAlreadyReturned;
import com.project.networktechproject.service.loan.error.LoanNotFound;
import com.project.networktechproject.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService extends OwnershipService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository, AuthRepository authRepository) {
        super(authRepository);
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @PostAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, returnObject.user.id)")
    public GetLoanResponseDto getOneById(long id) {
        LoanEntity loan = loanRepository
                .findById(id)
                .orElseThrow(() -> LoanNotFound.createWithId(id));

        return mapLoan(loan);
    }

    @PreAuthorize("hasRole('ADMIN') or (isAuthenticated() and this.isOwner(authentication.name, #userId))")
    public GetLoansPageResponseDto getAll(Long userId, int page, int size) {
        Page<LoanEntity> loansPage;

        Pageable pageable = PageRequest.of(page, size);

        if (userId == null) {
            loansPage = loanRepository.findAll(pageable);
        } else {
            loansPage = loanRepository.findByUserId(userId, pageable);
        }

        List<GetLoanResponseDto> loansDto = loansPage
                .stream()
                .map(this::mapLoan)
                .toList();

        return new GetLoansPageResponseDto(
                loansDto,
                loansPage.getNumber(),
                loansPage.getTotalElements(),
                loansPage.getTotalPages(),
                loansPage.hasNext()
        );
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #loanDto.userId)")
    public CreateLoanResponseDto create(CreateLoanDto loanDto) {
        Optional<LoanEntity> existingLoan = loanRepository
                .findByBookIdAndUserId(loanDto.getBookId(), loanDto.getUserId());

        if (existingLoan.isPresent()) {
            throw LoanAlreadyExists.create(loanDto.getBookId(), loanDto.getUserId());
        }

        var book = bookRepository
                .findById(loanDto.getBookId())
                .orElseThrow(() -> BookNotFound.create(loanDto.getBookId()));

        var user = userRepository
                .findById(loanDto.getUserId())
                .orElseThrow(() -> UserNotFound.createWithId(loanDto.getUserId()));

        LoanEntity loan = new LoanEntity();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDate.now());
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
            throw LoanNotFound.createWithId(id);
        }
        loanRepository.deleteById(id);
    }

    public ReturnLoanResponseDto returnBook(long id, Authentication authentication) {
        LoanEntity loan = loanRepository
                .findById(id)
                .orElseThrow(() -> LoanNotFound.createWithId(id));

        if (!isOwnerOrAdmin(getAuthInfo(authentication), loan.getUser().getId())) {
            throw Forbidden.create();
        }

        if (loan.getReturnDate() != null) {
            throw LoanAlreadyReturned.create(loan.getId());
        }

        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);

        return new ReturnLoanResponseDto(
                loan.getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getUser().getId(),
                loan.getBook().getId(),
                loan.getReturnDate()
        );
    }

    private GetLoanResponseDto mapLoan(LoanEntity loan) {
        GetUserDto user = new GetUserDto(
                loan.getUser().getId(),
                loan.getUser().getName(),
                loan.getUser().getLastName(),
                loan.getUser().getDateOfBirth(),
                loan.getUser().getEmail()
        );

        GetBookResponseDto book = new GetBookResponseDto(
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
                loan.getReturnDate(),
                user,
                book
        );
    }

}
