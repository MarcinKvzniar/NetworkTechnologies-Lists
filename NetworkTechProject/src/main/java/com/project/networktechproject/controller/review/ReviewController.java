package com.project.networktechproject.controller.review;

import com.project.networktechproject.controller.review.dto.CreateReviewDto;
import com.project.networktechproject.controller.review.dto.CreateReviewResponseDto;
import com.project.networktechproject.controller.review.dto.GetReviewResponseDto;
import com.project.networktechproject.controller.review.dto.GetReviewsPageResponseDto;
import com.project.networktechproject.service.review.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@PostAuthorize("isAuthenticated()")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetReviewResponseDto> getOneById(@PathVariable long id) {
        GetReviewResponseDto dto = reviewService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetReviewsPageResponseDto> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        GetReviewsPageResponseDto dto = reviewService.getAll(page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateReviewResponseDto> create(@Valid @RequestBody CreateReviewDto review) {
        var newReview = reviewService.create(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
