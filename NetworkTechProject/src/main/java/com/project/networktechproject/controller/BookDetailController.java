package com.project.networktechproject.controller;

import com.project.networktechproject.controller.dto.bookDetail.BookDetailDto;
import com.project.networktechproject.service.BookDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-details")
public class BookDetailController {

    private final BookDetailService bookDetailService;

    @Autowired
    public BookDetailController(BookDetailService bookDetailService) {
        this.bookDetailService = bookDetailService;
    }

    @PostMapping(path = "/add")
    public String addNewBookDetail(@RequestBody BookDetailDto bookDetailDTO) {
        bookDetailService.saveBookDetail(bookDetailDTO);
        return "Saved";
    }

    @GetMapping
    public List<BookDetailDto> getAllBookDetails() {
        return bookDetailService.getAllBookDetails();
    }
}
