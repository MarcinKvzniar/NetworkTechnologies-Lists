package com.project.networktechproject.controller;

import com.project.networktechproject.controller.dto.review.GetReviewDto;
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
    public String addNewReview(@RequestBody GetReviewDto getReviewDto) {
        reviewService.saveReview(getReviewDto);
        return "Saved";
    }

    @GetMapping
    public List<GetReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

}
