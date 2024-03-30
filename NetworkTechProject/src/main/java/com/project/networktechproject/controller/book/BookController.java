package com.project.networktechproject.controller.book;


import com.project.networktechproject.controller.book.dto.*;
import com.project.networktechproject.service.book.BookService;
import com.project.networktechproject.service.book.GoogleBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final GoogleBookService googleBookService;

    @Autowired
    public BookController(BookService bookService, GoogleBookService googleBookService) {
        this.bookService = bookService;
        this.googleBookService = googleBookService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GetBookDto> getOneById(@PathVariable long id) {
       GetBookDto dto = bookService.getOneById(id);
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetBooksPageResponseDto> getAllBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        GetBooksPageResponseDto dto = bookService.getAll(page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/details/{bookId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GoogleBookDetailDto> getBookDetails(@PathVariable String bookId) {
       GoogleBookDetailDto dto = googleBookService.getBookDetails(bookId);
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateBookResponseDto> create(@RequestBody CreateBookDto book) {
        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

