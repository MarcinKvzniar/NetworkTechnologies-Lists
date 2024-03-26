package com.project.networktechproject.controller.review.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class CreateReviewDto {
    @NotNull
    private int rating;

    private String comment;

    @NotNull
    private Date reviewDate;

    @NotNull
    private Long bookId;

    @NotNull
    private Long userId;

    public CreateReviewDto() {
    }

    public CreateReviewDto(int rating, String comment, Date reviewDate, Long bookId, Long userId) {
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

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
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
