package com.project.networktechproject.controller.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateBookDto {
    @NotBlank(message = "ISBN is mandatory")
    @Pattern(regexp = "\\d+", message = "ISBN must contain only numbers")
    @Schema(name = "isbn", example = "1234567890")
    private String isbn;

    @NotBlank(message = "Title is mandatory")
    @Schema(name = "title", example = "Title")
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Schema(name = "author", example = "Author")
    private String author;

    @Schema(name = "publisher", example = "Publisher", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String publisher;

    @Schema(name = "yearPublished", example = "2021", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int yearPublished;

    @Min(value = 1, message = "Available copies must be greater than 0")
    @Schema(name = "availableCopies", example = "10")
    private int availableCopies;

    public CreateBookDto() {
    }

    public CreateBookDto(String isbn, String title, String author, String publisher, int yearPublished, int availableCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.availableCopies = availableCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
