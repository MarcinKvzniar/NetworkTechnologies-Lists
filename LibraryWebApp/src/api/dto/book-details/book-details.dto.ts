import { BookDetailsItemDto } from './book-details-item.dto';

export class BookDetailsDto {
  items!: BookDetailsItemDto[];
  isAvailable!: boolean;
}
