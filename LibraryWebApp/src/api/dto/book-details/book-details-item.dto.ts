interface VolumeInfo {
  title: string;
  authors: string[];
  publishedDate: string;
  description: string;
  categories: string[];
  pageCount: number;
  imageLinks: {
    thumbnail: string;
  };
  language: string;
}

interface SaleInfo {
  isEbook: boolean;
}

export class BookDetailsItemDto {
  volumeInfo!: VolumeInfo;
  saleInfo!: SaleInfo;
}
