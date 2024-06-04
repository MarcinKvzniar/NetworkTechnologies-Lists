import { LoanResponseDto } from './loan-response.dto';

export class LoansPageDto {
  loans!: LoanResponseDto[];
  currentPage!: number;
  totalItems!: number;
  totalPages!: number;
  hasMore!: boolean;
}
