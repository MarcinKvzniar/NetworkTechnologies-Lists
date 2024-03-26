package com.project.networktechproject.controller.review.dto;

import com.project.networktechproject.controller.book.dto.GetBookDto;
import com.project.networktechproject.controller.user.dto.GetUserDto;

import java.sql.Date;

public class GetReviewResponseDto {
    private long id;
    private int rating;
    private String comment;
    private Date reviewDate;
    private GetUserDto user;
    private GetBookDto book;

    public GetReviewResponseDto() {
    }

    public GetReviewResponseDto(long id, int rating, String comment, Date reviewDate, GetUserDto user, GetBookDto book) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.user = user;
        this.book = book;
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

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public GetUserDto getUser() {
        return user;
    }

    public void setUser(GetUserDto user) {
        this.user = user;
    }

    public GetBookDto getBook() {
        return book;
    }

    public void setBook(GetBookDto book) {
        this.book = book;
    }
}
