package com.project.networktechproject.controller.dto.loan;

import com.project.networktechproject.controller.dto.book.GetBookDto;
import com.project.networktechproject.controller.dto.user.GetUserDto;
import com.project.networktechproject.infrastructure.entity.BookEntity;
import com.project.networktechproject.infrastructure.entity.UserEntity;

import java.time.LocalDate;

public class GetLoanDto {
    private long id;
    private BookEntity bookId;
    private UserEntity userId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public GetLoanDto(long id, BookEntity bookId, UserEntity userId, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public GetLoanDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookEntity getBookId() {
        return bookId;
    }

    public void setBookId(BookEntity bookId) {
        this.bookId = bookId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
