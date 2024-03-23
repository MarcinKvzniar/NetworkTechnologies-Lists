package com.project.networktechproject.service.book;

import com.project.networktechproject.controller.book.dto.CreateBookDto;
import com.project.networktechproject.controller.book.dto.CreateBookResponseDto;
import com.project.networktechproject.controller.book.dto.GetBookDto;
import com.project.networktechproject.infrastructure.entity.BookEntity;
import com.project.networktechproject.infrastructure.repository.BookRepository;
import com.project.networktechproject.service.book.error.BookAlreadyExists;
import com.project.networktechproject.service.book.error.BookNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetBookDto> getAll() {
        var books = bookRepository.findAll();

        return books
                .stream()
                .map(book -> new GetBookDto(
                    book.getId(),
                    book.getIsbn(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getYearPublished(),
                    book.getAvailableCopies() > 0
                ))
                .collect(Collectors.toList());
    }

    public GetBookDto getOne(long id) {
        var book = bookRepository
                .findById(id)
                .orElseThrow(() -> BookNotFound.create(id));

        return new GetBookDto(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getYearPublished(),
                book.getAvailableCopies() > 0
        );
    }

    public CreateBookResponseDto create(CreateBookDto book) {
        Optional<BookEntity> existingBook = bookRepository.findByIsbn(book.getIsbn());

        if (existingBook.isPresent()) {
            throw BookAlreadyExists.create(book.getIsbn());
        }

        var bookEntity = new BookEntity();
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setYearPublished(book.getYearPublished());
        bookEntity.setAvailableCopies(book.getAvailableCopies());

        var newBook =  bookRepository.save(bookEntity);

        return new CreateBookResponseDto(
                newBook.getId(),
                newBook.getIsbn(),
                newBook.getTitle(),
                newBook.getAuthor(),
                newBook.getPublisher(),
                newBook.getYearPublished(),
                newBook.getAvailableCopies()
        );
    }

    public void delete(long id) {
        if (!bookRepository.existsById(id)) {
            throw BookNotFound.create(id);
        }
        bookRepository.deleteById(id);
    }
}
