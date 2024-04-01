package com.project.networktechproject.service.book;

import com.project.networktechproject.controller.book.dto.GoogleBookDetailDto;
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

            if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
                GoogleBookDetailDto.Item item = response.getItems().get(0);
                GoogleBookDetailDto.VolumeInfo volumeInfo = item.getVolumeInfo();

                return extractResponse(volumeInfo);
            } else {
                throw BookDetailsNotFound.create(isbn);
            }
        } catch (RestClientException e) {
            throw new RestClientResponseException("Error while fetching book details", 500, "Error", null, null, null);
        }
    }

    private static GoogleBookDetailDto extractResponse(GoogleBookDetailDto.VolumeInfo volumeInfo) {
        String title = volumeInfo.getTitle();
        String language = volumeInfo.getLanguage();
        int pageCount = volumeInfo.getPageCount();
        List<String> categories = volumeInfo.getCategories();
        String description = volumeInfo.getDescription();
        String thumbnail = volumeInfo.getImageLinks() != null ? volumeInfo.getImageLinks().getThumbnail() : null;

        GoogleBookDetailDto.Item item = new GoogleBookDetailDto.Item();

        GoogleBookDetailDto.VolumeInfo resultVolumeInfo = new GoogleBookDetailDto.VolumeInfo();
        resultVolumeInfo.setTitle(title);
        resultVolumeInfo.setLanguage(language);
        resultVolumeInfo.setPageCount(pageCount);
        resultVolumeInfo.setCategories(categories);
        resultVolumeInfo.setDescription(description);

        GoogleBookDetailDto.ImageLinks resultImageLinks = new GoogleBookDetailDto.ImageLinks();
        resultImageLinks.setThumbnail(thumbnail);
        resultVolumeInfo.setImageLinks(resultImageLinks);

        item.setVolumeInfo(resultVolumeInfo);

        GoogleBookDetailDto result = new GoogleBookDetailDto();
        result.setItems(Collections.singletonList(item));

        return result;
    }
}
