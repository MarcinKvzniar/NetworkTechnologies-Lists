export class CreateBookResponseDto {
  id!: number;
  isbn!: string;
  title!: string;
  author!: string;
  publisher!: string;
  yearPublished!: number;
  availableCopies!: number;
}
