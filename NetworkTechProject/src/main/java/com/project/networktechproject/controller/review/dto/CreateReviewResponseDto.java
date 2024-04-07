package com.project.networktechproject.controller.review.dto;

import java.sql.Date;
import java.time.LocalDate;

public class CreateReviewResponseDto {
    private long id;
    private int rating;
    private String comment;
    private LocalDate reviewDate;
    private long userId;
    private long bookId;

    public CreateReviewResponseDto() {
    }

    public CreateReviewResponseDto(long id, int rating, String comment, LocalDate reviewDate, long userId, long bookId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.userId = userId;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
