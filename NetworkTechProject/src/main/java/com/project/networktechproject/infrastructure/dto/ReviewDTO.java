package com.project.networktechproject.infrastructure.dto;

import com.project.networktechproject.infrastructure.entity.BookEntity;
import com.project.networktechproject.infrastructure.entity.UserEntity;

import java.time.LocalDate;

public class ReviewDTO {
    private BookEntity bookId;
    private UserEntity userId;
    private int rating;
    private String comment;
    private LocalDate reviewDate;

    public BookEntity getBookId() { return bookId; }

    public void setBookId(BookEntity bookId) {
        this.bookId = bookId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
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
}
