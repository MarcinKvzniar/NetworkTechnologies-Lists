package com.project.networktechproject.service;

import com.project.networktechproject.infrastructure.entity.BookDetailEntity;
import com.project.networktechproject.infrastructure.repository.BookDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDetailService {

    private final BookDetailRepository bookDetailRepository;

    @Autowired
    public BookDetailService(BookDetailRepository bookDetailRepository) {
        this.bookDetailRepository = bookDetailRepository;
    }

    public List<BookDetailEntity> getAll() { return bookDetailRepository.findAll(); }
}
