package com.project.networktechproject.controller.loan.dto;

import java.time.LocalDate;

public class ReturnLoanResponseDto {

        private long id;
        private LocalDate loanDate;
        private LocalDate dueDate;
        private long userId;
        private long bookId;
        private LocalDate returnDate;


        public ReturnLoanResponseDto(long id, LocalDate loanDate, LocalDate dueDate, long userId, long bookId, LocalDate returnDate) {
            this.id = id;
            this.loanDate = loanDate;
            this.dueDate = dueDate;
            this.userId = userId;
            this.bookId = bookId;
            this.returnDate = returnDate;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

