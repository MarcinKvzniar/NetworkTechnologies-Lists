package com.project.networktechproject.controller.loan.dto;

import com.project.networktechproject.controller.book.dto.GetBookResponseDto;
import com.project.networktechproject.controller.user.dto.GetUserDto;

import java.sql.Date;
import java.time.LocalDate;

public class GetLoanResponseDto {
    private long id;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private GetUserDto user;
    private GetBookResponseDto book;

    public GetLoanResponseDto() {
    }

    public GetLoanResponseDto(long id, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, GetUserDto user, GetBookResponseDto book) {
        this.id = id;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.user = user;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public GetUserDto getUser() {
        return user;
    }

    public void setUser(GetUserDto user) {
        this.user = user;
    }

    public GetBookResponseDto getBook() {
        return book;
    }

    public void setBook(GetBookResponseDto book) {
        this.book = book;
    }
}
