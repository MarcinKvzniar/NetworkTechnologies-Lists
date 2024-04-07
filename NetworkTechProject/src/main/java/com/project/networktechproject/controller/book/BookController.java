package com.project.networktechproject.controller.book;


import com.project.networktechproject.controller.book.dto.*;
import com.project.networktechproject.service.book.BookService;
import com.project.networktechproject.service.book.GoogleBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
@PreAuthorize("isAuthenticated()")
public class BookController {

    private final BookService bookService;
    private final GoogleBookService googleBookService;

    @Autowired
    public BookController(BookService bookService, GoogleBookService googleBookService) {
        this.bookService = bookService;
        this.googleBookService = googleBookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBookResponseDto> getOneById(@PathVariable long id) {
       GetBookResponseDto dto = bookService.getOneById(id);
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetBooksPageResponseDto> getAllBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        GetBooksPageResponseDto dto = bookService.getAll(page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateBookResponseDto> create(@Valid @RequestBody CreateBookDto book) {
        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details/isbn/{isbn}")
    public ResponseEntity<GoogleBookDetailDto> getBookDetailsByIsbn(@PathVariable String isbn) {
       GoogleBookDetailDto dto = googleBookService.getBookDetailsByIsbn(isbn);
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/details/title/{title}")
    public ResponseEntity<GoogleBookDetailDto> getBookDetailsByTitle(@PathVariable String title) {
       GoogleBookDetailDto dto = googleBookService.getBookDetailByTitle(title);
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}

