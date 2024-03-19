package com.project.networktechproject.service;

import com.project.networktechproject.controller.dto.review.GetReviewDto;
import com.project.networktechproject.infrastructure.entity.ReviewEntity;
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

    public void saveReview(GetReviewDto getReviewDto) {
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setBookId(getReviewDto.getBookId());
        reviewEntity.setUserId(getReviewDto.getUserId());
        reviewEntity.setRating(getReviewDto.getRating());
        reviewEntity.setComment(getReviewDto.getComment());
        reviewEntity.setReviewDate(getReviewDto.getReviewDate());
        reviewRepository.save(reviewEntity);
    }

    public List<GetReviewDto> getAllReviews() {
        List<ReviewEntity> reviewEntities = reviewRepository.findAll();
        return reviewEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GetReviewDto convertToDTO(ReviewEntity reviewEntity) {
        GetReviewDto getReviewDto = new GetReviewDto();

        getReviewDto.setBookId(reviewEntity.getBookId());
        getReviewDto.setUserId(reviewEntity.getUserId());
        getReviewDto.setRating(reviewEntity.getRating());
        getReviewDto.setComment(reviewEntity.getComment());
        getReviewDto.setReviewDate(reviewEntity.getReviewDate());
        return getReviewDto;
    }
}
