package com.project.networktechproject.controller.review.dto;

import com.project.networktechproject.controller.book.dto.GetBookResponseDto;
import com.project.networktechproject.controller.user.dto.GetUserDto;

import java.sql.Date;
import java.time.LocalDate;

public class GetReviewResponseDto {
    private long id;
    private int rating;
    private String comment;
    private LocalDate reviewDate;
    private GetUserDto user;
    private GetBookResponseDto book;

    public GetReviewResponseDto() {
    }

    public GetReviewResponseDto(long id, int rating, String comment, LocalDate reviewDate, GetUserDto user, GetBookResponseDto book) {
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

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
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
