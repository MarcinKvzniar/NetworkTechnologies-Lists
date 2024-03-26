package com.project.networktechproject.service.book;

import com.project.networktechproject.controller.book.dto.GoogleBookDetailDto;
import com.project.networktechproject.controller.book.dto.CreateBookDto;
import com.project.networktechproject.controller.book.dto.CreateBookResponseDto;
import com.project.networktechproject.controller.book.dto.GetBookDto;
import com.project.networktechproject.infrastructure.entity.BookEntity;
import com.project.networktechproject.infrastructure.repository.BookRepository;
import com.project.networktechproject.service.book.error.BookAlreadyExists;
import com.project.networktechproject.service.book.error.BookDetailsNotFound;
import com.project.networktechproject.service.book.error.BookNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final RestTemplate restTemplate;

    @Value("${app.api-key}")
    private String apiKey;

    @Autowired
    public BookService(BookRepository bookRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.restTemplate = restTemplate;
    }

    public GetBookDto getOneById(long id) {
        BookEntity book = bookRepository
                .findById(id)
                .orElseThrow(() -> BookNotFound.create(id));

        return mapBook(book);
    }

    public List<GetBookDto> getAll() {
        List<BookEntity> books = bookRepository.findAll();

        return books
                .stream()
                .map(this::mapBook)
                .collect(Collectors.toList());
    }

    public CreateBookResponseDto create(CreateBookDto bookDto) {
        Optional<BookEntity> existingBook = bookRepository.findByIsbn(bookDto.getIsbn());

        if (existingBook.isPresent()) {
            throw BookAlreadyExists.create(bookDto.getIsbn());
        }

        BookEntity book = new BookEntity();
        book.setIsbn(book.getIsbn());
        book.setTitle(book.getTitle());
        book.setAuthor(book.getAuthor());
        book.setPublisher(book.getPublisher());
        book.setYearPublished(book.getYearPublished());
        book.setAvailableCopies(book.getAvailableCopies());
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

    public GoogleBookDetailDto getBookDetail (String bookId) {
        try {
            String url = "https://www.googleapis.com/books/v1/volumes/" + bookId + "?key=" + apiKey;
            return restTemplate.getForObject(url, GoogleBookDetailDto.class);
         } catch (RestClientException e) {
            throw BookDetailsNotFound.create(bookId);
        }
    }

    private GetBookDto mapBook(BookEntity book) {
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

}
