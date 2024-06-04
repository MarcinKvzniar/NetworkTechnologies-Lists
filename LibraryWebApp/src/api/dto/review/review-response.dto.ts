import { BookResponseDto } from '../book/book-response.dto';
import { UserDto } from '../user/user.dto';

export class ReviewResponseDto {
  id!: number;
  rating!: number;
  comment!: string;
  reviewDate!: string;
  user!: UserDto[];
  book!: BookResponseDto[];
}
