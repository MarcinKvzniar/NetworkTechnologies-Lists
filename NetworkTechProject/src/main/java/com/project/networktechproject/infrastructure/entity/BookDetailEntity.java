package com.project.networktechproject.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_details", schema = "library")
public class BookDetailEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @OneToOne
    @JoinColumn(name = "book_id")
    private BookEntity bookId;
    @Basic
    @Column(name = "genre")
    private String genre;
    @Basic
    @Column(name = "summary")
    private String summary;
    @Basic
    @Column(name = "cover_image_url")
    private String coverImageUrl;

    public BookEntity getBookId() {
        return bookId;
    }

    public void setBookId(BookEntity bookId) {
        this.bookId = bookId;
    }
}
