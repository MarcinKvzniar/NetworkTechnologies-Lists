import { BookResponseDto } from './book-response.dto';

export class BooksPageDto {
  books!: BookResponseDto[];
  currentPage!: number;
  totalPages!: number;
  totalItems!: number;
  hasMore!: boolean;
}
