package com.project.networktechproject.controller.book;


import com.project.networktechproject.controller.book.dto.BookDetailDto;
import com.project.networktechproject.controller.book.dto.CreateBookDto;
import com.project.networktechproject.controller.book.dto.CreateBookResponseDto;
import com.project.networktechproject.controller.book.dto.GetBookDto;
import com.project.networktechproject.service.book.BookService;
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

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<GetBookDto> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public GetBookDto getOne(@PathVariable long id) {
        return bookService.getOne(id);
    }

    @GetMapping("/details/{bookId}")
    @PreAuthorize("permitAll()")
    public BookDetailDto getBookDetails(@PathVariable String bookId) {
        return bookService.getBookDetail(bookId);
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

