package com.project.networktechproject.controller.loan.dto;

import com.project.networktechproject.controller.book.dto.GetBookResponseDto;
import com.project.networktechproject.controller.user.dto.GetUserDto;

import java.sql.Date;

public class GetLoanResponseDto {
    private long id;
    private Date loanDate;
    private Date dueDate;
    private GetUserDto user;
    private GetBookResponseDto book;

    public GetLoanResponseDto() {
    }

    public GetLoanResponseDto(long id, Date loanDate, Date dueDate, GetUserDto user, GetBookResponseDto book) {
        this.id = id;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.user = user;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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
