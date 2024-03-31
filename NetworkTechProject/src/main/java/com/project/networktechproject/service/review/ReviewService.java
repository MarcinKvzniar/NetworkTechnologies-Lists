package com.project.networktechproject.service.review;

import com.project.networktechproject.controller.book.dto.GetBookResponseDto;
import com.project.networktechproject.controller.review.dto.CreateReviewDto;
import com.project.networktechproject.controller.review.dto.CreateReviewResponseDto;
import com.project.networktechproject.controller.review.dto.GetReviewResponseDto;
import com.project.networktechproject.controller.review.dto.GetReviewsPageResponseDto;
import com.project.networktechproject.controller.user.dto.GetUserDto;
import com.project.networktechproject.infrastructure.entity.ReviewEntity;
import com.project.networktechproject.infrastructure.repository.AuthRepository;
import com.project.networktechproject.infrastructure.repository.BookRepository;
import com.project.networktechproject.infrastructure.repository.ReviewRepository;
import com.project.networktechproject.infrastructure.repository.UserRepository;
import com.project.networktechproject.service.auth.OwnershipService;
import com.project.networktechproject.service.book.error.BookNotFound;
import com.project.networktechproject.service.review.error.ReviewNotFound;
import com.project.networktechproject.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService extends OwnershipService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository, AuthRepository authRepository) {
        super(authRepository);
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @PreAuthorize("isAuthenticated()")
    public GetReviewResponseDto getOneById(long id) {
        ReviewEntity review = reviewRepository
                .findById(id)
                .orElseThrow(() -> ReviewNotFound.create(id));

        return mapReview(review);
    }

    @PreAuthorize("isAuthenticated()")
    public GetReviewsPageResponseDto getAll(int page, int size) {
        Page<ReviewEntity> reviewsPage;

        Pageable pageable = PageRequest.of(page, size);

        reviewsPage = reviewRepository.findAll(pageable);

        List<GetReviewResponseDto> reviewsDto = reviewsPage
                .stream()
                .map(this::mapReview)
                .toList();

        return new GetReviewsPageResponseDto(
                reviewsDto,
                reviewsPage.getNumber(),
                reviewsPage.getTotalElements(),
                reviewsPage.getTotalPages(),
                reviewsPage.hasNext()
        );
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #reviewDto.userId)")
    public CreateReviewResponseDto create(CreateReviewDto reviewDto) {
        /*/
            I assume that user can create more than one review per book,
            so I don't check if the user has already reviewed the book.
        /*/
        var book = bookRepository
                .findById(reviewDto.getBookId())
                .orElseThrow(() -> BookNotFound.create(reviewDto.getBookId()));

        var user = userRepository
                .findById(reviewDto.getUserId())
                .orElseThrow(() -> UserNotFound.createWithId(reviewDto.getUserId()));

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

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #reviewDto.userId)")
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

        GetBookResponseDto book = new GetBookResponseDto(
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
