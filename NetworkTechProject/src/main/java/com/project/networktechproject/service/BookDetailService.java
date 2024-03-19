package com.project.networktechproject.service;

import com.project.networktechproject.controller.dto.bookDetail.BookDetailDto;
import com.project.networktechproject.infrastructure.entity.BookDetailEntity;
import com.project.networktechproject.infrastructure.repository.BookDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookDetailService {

    private final BookDetailRepository bookDetailRepository;

    @Autowired
    public BookDetailService(BookDetailRepository bookDetailRepository) {
        this.bookDetailRepository = bookDetailRepository;
    }

    public void saveBookDetail(BookDetailDto bookDetailDTO) {
        BookDetailEntity bookDetailEntity = new BookDetailEntity();

        bookDetailEntity.setBookId(bookDetailDTO.getBookId());
        bookDetailEntity.setGenre(bookDetailDTO.getGenre());
        bookDetailEntity.setSummary(bookDetailDTO.getSummary());
        bookDetailEntity.setCoverImageUrl(bookDetailDTO.getCoverImageUrl());
        bookDetailRepository.save(bookDetailEntity);
    }

    public List<BookDetailDto> getAllBookDetails() {
        List<BookDetailEntity> bookDetailEntities = bookDetailRepository.findAll();
        return bookDetailEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BookDetailDto convertToDTO(BookDetailEntity bookDetailEntity) {
        BookDetailDto bookDetailDTO = new BookDetailDto();

        bookDetailDTO.setBookId(bookDetailEntity.getBookId());
        bookDetailDTO.setGenre(bookDetailEntity.getGenre());
        bookDetailDTO.setSummary(bookDetailEntity.getSummary());
        bookDetailDTO.setCoverImageUrl(bookDetailEntity.getCoverImageUrl());
        return bookDetailDTO;
    }
}
