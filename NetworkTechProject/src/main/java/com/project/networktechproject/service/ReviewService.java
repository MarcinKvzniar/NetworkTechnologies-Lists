package com.project.networktechproject.service;

import com.project.networktechproject.infrastructure.entity.ReviewEntity;
import com.project.networktechproject.infrastructure.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewEntity> getAll() {
        return reviewRepository.findAll();
    }
}
