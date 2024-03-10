package com.project.networktechproject.infrastructure.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans", schema = "library")
public class LoanEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity bookId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;
    @Basic
    @Column(name = "loan_date")
    private LocalDate loanDate;
    @Basic
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Basic
    @Column(name = "return_date")
    private LocalDate returnDate;

    public BookEntity getBookId() {
        return bookId;
    }

    public void setBookId(BookEntity bookId) {
        this.bookId = bookId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }
}
