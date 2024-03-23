package com.project.networktechproject.controller.review;

import com.project.networktechproject.controller.review.dto.CreateReviewDto;
import com.project.networktechproject.controller.review.dto.CreateReviewResponseDto;
import com.project.networktechproject.controller.review.dto.GetReviewDto;
import com.project.networktechproject.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<GetReviewDto> getAll() {
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public GetReviewDto getOne(@PathVariable long id) {
        return reviewService.getOne(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody CreateReviewDto review) {
        var newReview = reviewService.create(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
