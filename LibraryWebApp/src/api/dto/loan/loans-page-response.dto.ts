import { LoanResponseDto } from './loan-response.dto';

export class LoansPageResponseDto {
  loans!: LoanResponseDto[];
  currentPage!: number;
  totalItems!: number;
  totalPages!: number;
  hasMore!: boolean;
}
