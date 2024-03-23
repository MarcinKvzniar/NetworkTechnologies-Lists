package com.project.networktechproject.service.review;

import com.project.networktechproject.controller.review.dto.CreateReviewDto;
import com.project.networktechproject.controller.review.dto.CreateReviewResponseDto;
import com.project.networktechproject.controller.review.dto.GetReviewDto;
import com.project.networktechproject.infrastructure.entity.ReviewEntity;
import com.project.networktechproject.infrastructure.repository.BookRepository;
import com.project.networktechproject.infrastructure.repository.ReviewRepository;
import com.project.networktechproject.infrastructure.repository.UserRepository;
import com.project.networktechproject.service.book.error.BookNotFound;
import com.project.networktechproject.service.review.error.ReviewNotFound;
import com.project.networktechproject.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
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
                ))
                .collect(Collectors.toList());
    }

    public GetReviewDto getOne(long id) {
        var review = reviewRepository
                .findById(id)
                .orElseThrow(() -> ReviewNotFound.create(id));

        return new GetReviewDto(
                review.getId(),
                review.getBookId(),
                review.getUserId(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate()
        );
    }

    public CreateReviewResponseDto create(CreateReviewDto review) {
        // I assumed that user can create more than one review per book, so
        // I didn't check if the user has already reviewed the book.
        var reviewEntity = new ReviewEntity();

        var bookEntity = bookRepository
                .findById(review.getBookId().getId())
                .orElseThrow(() -> BookNotFound.create(review.getBookId().getId()));

        var userEntity = userRepository.findById(review.getUserId().getId())
                .orElseThrow(() -> UserNotFound.create(review.getUserId().getId()));

        reviewEntity.setBookId(bookEntity);
        reviewEntity.setUserId(userEntity);
        reviewEntity.setRating(review.getRating());
        reviewEntity.setComment(review.getComment());
        reviewEntity.setReviewDate(review.getReviewDate());

        var newReview = reviewRepository.save(reviewEntity);

        return new CreateReviewResponseDto(
                newReview.getId(),
                newReview.getBookId(),
                newReview.getUserId(),
                newReview.getRating(),
                newReview.getComment(),
                newReview.getReviewDate()
        );
    }

    public void delete(long id) {
        if (!reviewRepository.existsById(id)) {
            throw ReviewNotFound.create(id);
        }
        reviewRepository.deleteById(id);
    }
}
