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

    public GoogleBookDetailDto getBookDetailsByIsbn (String isbn) {
        try {
            String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn + "&key=" + apiKey;
            GoogleBookDetailDto response = restTemplate.getForObject(url, GoogleBookDetailDto.class);

            if (response != null) {
                String language = response.getLanguage();
                int pageCount = response.getPageCount();
                String categories = response.getCategories();
                String description = response.getDescription();
                String thumbnail = null;

                if (response.getImageLinks() != null) {
                    thumbnail = response.getImageLinks().getThumbnail();
                }

                GoogleBookDetailDto bookDetails = new GoogleBookDetailDto();
                bookDetails.setLanguage(language);
                bookDetails.setPageCount(pageCount);
                bookDetails.setCategories(categories);
                bookDetails.setDescription(description);

                GoogleBookDetailDto.ImageLinks imageLinks = new GoogleBookDetailDto.ImageLinks();
                imageLinks.setThumbnail(thumbnail);
                bookDetails.setImageLinks(imageLinks);

                return bookDetails;
            } else {
                throw BookDetailsNotFound.create(isbn);
            }
        } catch (RestClientException e) {
            throw new RestClientResponseException("Error while fetching book details", 500, "Error", null, null, null);
        }
    }
}
