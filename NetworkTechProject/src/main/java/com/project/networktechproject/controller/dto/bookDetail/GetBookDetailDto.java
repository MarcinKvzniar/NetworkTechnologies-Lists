package com.project.networktechproject.controller.dto.bookDetail;

import com.project.networktechproject.infrastructure.entity.BookEntity;

public class GetBookDetailDto {
    private BookEntity bookId;
    private String genre;
    private String summary;
    private String coverImageUrl;

    public BookEntity getBookId() {
        return bookId;
    }

    public void setBookId(BookEntity bookId) {
        this.bookId = bookId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
