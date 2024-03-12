package com.project.networktechproject.service;

import com.project.networktechproject.infrastructure.dto.BookDTO;
import com.project.networktechproject.infrastructure.entity.BookEntity;
import com.project.networktechproject.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBook(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setPublisher(bookDTO.getPublisher());
        bookEntity.setYearPublished(bookDTO.getYearPublished());
        bookEntity.setAvailableCopies(bookDTO.getAvailableCopies());
        bookRepository.save(bookEntity);
    }

    public List<BookDTO> getAllBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        return bookEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BookDTO convertToDTO(BookEntity bookEntity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn(bookEntity.getIsbn());
        bookDTO.setTitle(bookEntity.getTitle());
        bookDTO.setAuthor(bookEntity.getAuthor());
        bookDTO.setPublisher(bookEntity.getPublisher());
        bookDTO.setYearPublished(bookEntity.getYearPublished());
        bookDTO.setAvailableCopies(bookEntity.getAvailableCopies());
        return bookDTO;
    }
}
