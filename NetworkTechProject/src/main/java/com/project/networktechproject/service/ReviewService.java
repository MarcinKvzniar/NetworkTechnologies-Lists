package com.project.networktechproject.service;

import com.project.networktechproject.controller.review.dto.GetReviewDto;
import com.project.networktechproject.infrastructure.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<GetReviewDto> getAll() {
        var reviews = reviewRepository.findAll();

        return reviews
                .stream()
                .map(review -> new GetReviewDto(
                        review.getId(),
                        review.getBookId(),
                        review.getUserId(),
                        review.getRating(),
                        review.getComment(),
                        review.getReviewDate()
                )).collect(Collectors.toList());
    }

    public GetReviewDto getOne(long id) {
        var review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        return new GetReviewDto(
                review.getId(),
                review.getBookId(),
                review.getUserId(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate()
        );
    }
}
