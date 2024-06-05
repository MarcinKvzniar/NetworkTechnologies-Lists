import { UserDto } from './user.dto';

export class UsersPageDto {
  users!: UserDto[];
  currentPage!: number;
  totalPages!: number;
  totalItems!: number;
  hasMore!: boolean;
}
