package com.project.networktechproject.controller.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateLoanDto {
    @NotNull
    @Schema(name = "dueDate", example = "2024-04-07")
    private LocalDate dueDate;

    @NotNull
    @Schema(name = "userId", example = "1")
    private Long userId;

    @NotNull
    @Schema(name = "bookId", example = "1")
    private Long bookId;

    public CreateLoanDto() {
    }

    public CreateLoanDto(LocalDate dueDate, Long userId, Long bookId) {
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

}
