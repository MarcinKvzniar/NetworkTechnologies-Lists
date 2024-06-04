interface volumeInfo {
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

interface saleInfo {
  ebook: boolean;
}

export class BookDetailsItemDto {
  volumeInfo!: volumeInfo;
  saleInfo!: saleInfo;
}
