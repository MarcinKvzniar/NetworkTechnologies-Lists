package com.project.networktechproject.controller.loan.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class CreateLoanDto {
    @NotNull
    private Date dueDate;

    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;

    public CreateLoanDto() {
    }

    public CreateLoanDto(Date dueDate, Long userId, Long bookId) {
        this.dueDate = dueDate;
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

}
