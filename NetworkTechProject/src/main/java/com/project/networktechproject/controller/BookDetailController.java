package com.project.networktechproject.controller;

import com.project.networktechproject.service.BookDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookDetails")
public class BookDetailController {

    private final BookDetailService bookDetailService;

    @Autowired
    public BookDetailController(BookDetailService bookDetailService) {
        this.bookDetailService = bookDetailService;
    }

    @GetMapping
    String getAll() {
        return "Mock all";
    }
}
