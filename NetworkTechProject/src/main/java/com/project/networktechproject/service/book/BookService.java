package com.project.networktechproject.service.book;

import com.project.networktechproject.controller.book.dto.CreateBookDto;
import com.project.networktechproject.controller.book.dto.CreateBookResponseDto;
import com.project.networktechproject.controller.book.dto.GetBookResponseDto;
import com.project.networktechproject.controller.book.dto.GetBooksPageResponseDto;
import com.project.networktechproject.infrastructure.entity.BookEntity;
import com.project.networktechproject.infrastructure.repository.BookRepository;
import com.project.networktechproject.service.book.error.BookAlreadyExists;
import com.project.networktechproject.service.book.error.BookNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public GetBookResponseDto getOneById(long id) {
        BookEntity book = bookRepository
                .findById(id)
                .orElseThrow(() -> BookNotFound.create(id));

        return mapBook(book);
    }

    @PreAuthorize("isAuthenticated()")
    public GetBooksPageResponseDto getAll(int page, int size) {
        Page<BookEntity> booksPage;

        Pageable pageable = PageRequest.of(page, size);

        booksPage = bookRepository.findAll(pageable);

        List<GetBookResponseDto> booksDto = booksPage
                .stream()
                .map(this::mapBook)
                .toList();

        return new GetBooksPageResponseDto(
                booksDto,
                booksPage.getNumber(),
                booksPage.getTotalElements(),
                booksPage.getTotalPages(),
                booksPage.hasNext()
        );
    }

    public CreateBookResponseDto create(CreateBookDto bookDto) {
        Optional<BookEntity> existingBook = bookRepository.findByIsbn(bookDto.getIsbn());

        if (existingBook.isPresent()) {
            throw BookAlreadyExists.create(bookDto.getIsbn());
        }

        BookEntity book = new BookEntity();
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());
        book.setYearPublished(bookDto.getYearPublished());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(book);

        return new CreateBookResponseDto(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getYearPublished(),
                book.getAvailableCopies()
        );
    }

    public void delete(long id) {
        if (!bookRepository.existsById(id)) {
            throw BookNotFound.create(id);
        }
        bookRepository.deleteById(id);
    }

    private GetBookResponseDto mapBook(BookEntity book) {
        return new GetBookResponseDto(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getYearPublished(),
                book.getAvailableCopies() > 0
        );
    }

}
