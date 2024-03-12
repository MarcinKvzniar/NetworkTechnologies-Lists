package com.project.networktechproject.service;

import com.project.networktechproject.infrastructure.dto.ReviewDTO;
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

    public void saveReview(ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setBookId(reviewDTO.getBookId());
        reviewEntity.setUserId(reviewDTO.getUserId());
        reviewEntity.setRating(reviewDTO.getRating());
        reviewEntity.setComment(reviewDTO.getComment());
        reviewEntity.setReviewDate(reviewDTO.getReviewDate());
        reviewRepository.save(reviewEntity);
    }

    public List<ReviewDTO> getAllReviews() {
        List<ReviewEntity> reviewEntities = reviewRepository.findAll();
        return reviewEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ReviewDTO convertToDTO(ReviewEntity reviewEntity) {
        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setBookId(reviewEntity.getBookId());
        reviewDTO.setUserId(reviewEntity.getUserId());
        reviewDTO.setRating(reviewEntity.getRating());
        reviewDTO.setComment(reviewEntity.getComment());
        reviewDTO.setReviewDate(reviewEntity.getReviewDate());
        return reviewDTO;
    }
}
