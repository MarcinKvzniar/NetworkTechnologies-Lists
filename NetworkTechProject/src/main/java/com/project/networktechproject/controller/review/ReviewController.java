package com.project.networktechproject.controller.review;

import com.project.networktechproject.controller.review.dto.CreateReviewDto;
import com.project.networktechproject.controller.review.dto.CreateReviewResponseDto;
import com.project.networktechproject.controller.review.dto.GetReviewResponseDto;
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

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<GetReviewResponseDto> getOneById(@PathVariable long id) {
        GetReviewResponseDto dto = reviewService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<GetReviewResponseDto>> getAll() {
        List<GetReviewResponseDto> dto = reviewService.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
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
