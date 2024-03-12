package com.project.networktechproject.controller;


import com.project.networktechproject.infrastructure.dto.BookDTO;
import com.project.networktechproject.infrastructure.repository.BookRepository;
import com.project.networktechproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.networktechproject.infrastructure.entity.BookEntity;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewBook (@RequestBody BookDTO bookDTO) {
        bookService.saveBook(bookDTO);
        return "Saved";
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
}
