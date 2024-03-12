package com.project.networktechproject.controller;

import com.project.networktechproject.infrastructure.dto.ReviewDTO;
import com.project.networktechproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(path = "/add")
    public String addNewReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.saveReview(reviewDTO);
        return "Saved";
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

}
