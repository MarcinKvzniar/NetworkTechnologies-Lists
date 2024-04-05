package com.project.networktechproject.service.book;

import com.project.networktechproject.controller.book.dto.GoogleBookDetailDto;
import com.project.networktechproject.infrastructure.repository.BookRepository;
import com.project.networktechproject.service.book.error.BookDetailsNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class GoogleBookService {
    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    @Value("${app.api-key}")
    private String apiKey;

    @Autowired
    public GoogleBookService(RestTemplate restTemplate, BookRepository bookRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
    }

    public GoogleBookDetailDto getBookDetailsByIsbn (String isbn) {
        try {
            String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn + "&key=" + apiKey;
            GoogleBookDetailDto response = restTemplate.getForObject(url, GoogleBookDetailDto.class);

            if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
                GoogleBookDetailDto.Item item = response.getItems().get(0);
                GoogleBookDetailDto.VolumeInfo volumeInfo = item.getVolumeInfo();
                GoogleBookDetailDto.SaleInfo saleInfo = item.getSaleInfo();

                GoogleBookDetailDto result = extractResponse(volumeInfo, saleInfo);

                boolean isAvailable = bookRepository.findByIsbn(isbn).isPresent();
                result.setAvailable(isAvailable);

                return result;
            } else {
                throw BookDetailsNotFound.create(isbn);
            }
        } catch (RestClientException e) {
            throw new RestClientResponseException("Error while fetching book details", 500, "Error", null, null, null);
        }
    }

    public GoogleBookDetailDto getBookDetailByTitle (String title) {
        try {
            String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title + "&key=" + apiKey;
            GoogleBookDetailDto response = restTemplate.getForObject(url, GoogleBookDetailDto.class);

            if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
                GoogleBookDetailDto.Item item = response.getItems().get(0);
                GoogleBookDetailDto.VolumeInfo volumeInfo = item.getVolumeInfo();
                GoogleBookDetailDto.SaleInfo saleInfo = item.getSaleInfo();

                GoogleBookDetailDto result = extractResponse(volumeInfo, saleInfo);

                String queryTitle = volumeInfo.getTitle();

                boolean isAvailable = bookRepository.findByTitle(queryTitle).isPresent();
                result.setAvailable(isAvailable);

                return result;
            } else {
                throw BookDetailsNotFound.create(title);
            }
        } catch (RestClientException e) {
            throw new RestClientResponseException("Error while fetching book details", 500, "Error", null, null, null);
        }
    }

    private static GoogleBookDetailDto extractResponse(GoogleBookDetailDto.VolumeInfo volumeInfo, GoogleBookDetailDto.SaleInfo saleInfo) {
        String title = volumeInfo.getTitle();
        List<String> authors = volumeInfo.getAuthors();
        String publishedDate = volumeInfo.getPublishedDate();
        String language = volumeInfo.getLanguage();
        int pageCount = volumeInfo.getPageCount();
        List<String> categories = volumeInfo.getCategories();
        String description = volumeInfo.getDescription();
        String thumbnail = volumeInfo.getImageLinks() != null ? volumeInfo.getImageLinks().getThumbnail() : null;
        boolean isEbook = saleInfo.isEbook();

        GoogleBookDetailDto.Item item = new GoogleBookDetailDto.Item();

        GoogleBookDetailDto.VolumeInfo resultVolumeInfo = new GoogleBookDetailDto.VolumeInfo();
        resultVolumeInfo.setTitle(title);
        resultVolumeInfo.setAuthors(authors);
        resultVolumeInfo.setPublishedDate(publishedDate);
        resultVolumeInfo.setLanguage(language);
        resultVolumeInfo.setPageCount(pageCount);
        resultVolumeInfo.setCategories(categories);
        resultVolumeInfo.setDescription(description);

        GoogleBookDetailDto.ImageLinks resultImageLinks = new GoogleBookDetailDto.ImageLinks();
        resultImageLinks.setThumbnail(thumbnail);
        resultVolumeInfo.setImageLinks(resultImageLinks);

        GoogleBookDetailDto.SaleInfo resultSaleInfo = new GoogleBookDetailDto.SaleInfo();
        resultSaleInfo.setEbook(isEbook);

        item.setVolumeInfo(resultVolumeInfo);
        item.setSaleInfo(resultSaleInfo);

        GoogleBookDetailDto result = new GoogleBookDetailDto();
        result.setItems(Collections.singletonList(item));

        return result;
    }
}
