package com.project.networktechproject.service.book;

import com.project.networktechproject.controller.book.dto.GoogleBookDetailDto;
import com.project.networktechproject.service.book.error.BookDetailsNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleBookService {
    private final RestTemplate restTemplate;

    @Value("${app.api-key}")
    private String apiKey;

    @Autowired
    public GoogleBookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GoogleBookDetailDto getBookDetails (String bookId) {
        try {
            String url = "https://www.googleapis.com/books/v1/volumes/" + bookId + "?key=" + apiKey;
            return restTemplate.getForObject(url, GoogleBookDetailDto.class);
        } catch (RestClientException e) {
            throw new RestClientResponseException("Error while fetching book details", 500, "Error", null, null, null);
        } catch (RuntimeException e) {
            throw BookDetailsNotFound.create(bookId);
        }
    }
}
