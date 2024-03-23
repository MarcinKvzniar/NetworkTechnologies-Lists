package com.project.networktechproject.service.bookDetail;

import com.project.networktechproject.controller.bookDetail.dto.GetBookDetailDto;
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

    public void saveBookDetail(GetBookDetailDto getBookDetailDTO) {
        BookDetailEntity bookDetailEntity = new BookDetailEntity();

        bookDetailEntity.setBookId(getBookDetailDTO.getBookId());
        bookDetailEntity.setGenre(getBookDetailDTO.getGenre());
        bookDetailEntity.setSummary(getBookDetailDTO.getSummary());
        bookDetailEntity.setCoverImageUrl(getBookDetailDTO.getCoverImageUrl());
        bookDetailRepository.save(bookDetailEntity);
    }

    public List<GetBookDetailDto> getAllBookDetails() {
        List<BookDetailEntity> bookDetailEntities = bookDetailRepository.findAll();
        return bookDetailEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GetBookDetailDto convertToDTO(BookDetailEntity bookDetailEntity) {
        GetBookDetailDto getBookDetailDTO = new GetBookDetailDto();

        getBookDetailDTO.setBookId(bookDetailEntity.getBookId());
        getBookDetailDTO.setGenre(bookDetailEntity.getGenre());
        getBookDetailDTO.setSummary(bookDetailEntity.getSummary());
        getBookDetailDTO.setCoverImageUrl(bookDetailEntity.getCoverImageUrl());
        return getBookDetailDTO;
    }
}
