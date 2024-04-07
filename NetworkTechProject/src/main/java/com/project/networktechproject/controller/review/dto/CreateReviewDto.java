package com.project.networktechproject.controller.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

public class CreateReviewDto {

    @Min(1)
    @Max(10)
    @NotNull
    @Schema(name = "rating", example = "8")
    private int rating;

    @Schema(name = "comment", example = "This book is amazing!", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String comment;

    @NotNull
    @Schema(name = "reviewDate", example = "2024-04-07")
    private LocalDate reviewDate;

    @NotNull
    @Schema(name = "bookId", example = "1")
    private Long bookId;

    @NotNull
    @Schema(name = "userId", example = "1")
    private Long userId;

    public CreateReviewDto() {
    }

    public CreateReviewDto(int rating, String comment, LocalDate reviewDate, Long bookId, Long userId) {
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.bookId = bookId;
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
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
}
