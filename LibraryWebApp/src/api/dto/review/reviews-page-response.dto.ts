import { ReviewResponseDto } from './review-response.dto';

export class ReviewsPageResponseDto {
  reviews!: ReviewResponseDto[];
  currentPage!: number;
  totalItems!: number;
  totalPages!: number;
  hasMore!: boolean;
}
