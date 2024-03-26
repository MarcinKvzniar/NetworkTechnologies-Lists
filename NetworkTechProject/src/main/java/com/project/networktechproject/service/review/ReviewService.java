package com.project.networktechproject.service.review;

import com.project.networktechproject.controller.book.dto.GetBookDto;
import com.project.networktechproject.controller.loan.dto.GetLoanResponseDto;
import com.project.networktechproject.controller.review.dto.CreateReviewDto;
import com.project.networktechproject.controller.review.dto.CreateReviewResponseDto;
import com.project.networktechproject.controller.review.dto.GetReviewResponseDto;
import com.project.networktechproject.controller.user.dto.GetUserDto;
import com.project.networktechproject.infrastructure.entity.LoanEntity;
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

    public List<GetReviewResponseDto> getAll() {
        List<ReviewEntity> reviews = reviewRepository.findAll();

        return reviews
                .stream()
                .map(this::mapReview)
                .collect(Collectors.toList());
    }

    public GetReviewResponseDto getOneById(long id) {
        ReviewEntity review = reviewRepository
                .findById(id)
                .orElseThrow(() -> ReviewNotFound.create(id));

        return mapReview(review);
    }

    public CreateReviewResponseDto create(CreateReviewDto reviewDto) {
        /*/
            I assume that user can create more than one review per book,
            so I don't check if the user has already reviewed the book.
        /*/
        var book = bookRepository
                .findById(reviewDto.getBookId())
                .orElseThrow(() -> BookNotFound.create(reviewDto.getBookId()));

        var user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> UserNotFound.create(reviewDto.getUserId()));

        ReviewEntity review = new ReviewEntity();
        review.setBook(book);
        review.setUser(user);
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setReviewDate(reviewDto.getReviewDate());
        reviewRepository.save(review);

        return new CreateReviewResponseDto(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate(),
                review.getUser().getId(),
                review.getBook().getId()
        );
    }

    public void delete(long id) {
        if (!reviewRepository.existsById(id)) {
            throw ReviewNotFound.create(id);
        }
        reviewRepository.deleteById(id);
    }

    private GetReviewResponseDto mapReview(ReviewEntity review) {
        GetUserDto user = new GetUserDto(
                review.getUser().getId(),
                review.getUser().getName(),
                review.getUser().getLastName(),
                review.getUser().getDateOfBirth(),
                review.getUser().getEmail()
        );

        GetBookDto book = new GetBookDto(
                review.getBook().getId(),
                review.getBook().getIsbn(),
                review.getBook().getTitle(),
                review.getBook().getAuthor(),
                review.getBook().getPublisher(),
                review.getBook().getYearPublished(),
                review.getBook().getAvailableCopies() > 0
        );

        return new GetReviewResponseDto(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate(),
                user,
                book
        );
    }
}
