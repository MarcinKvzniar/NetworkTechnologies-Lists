import axios, { AxiosError, AxiosInstance, AxiosResponse } from 'axios';
import { LoginRequestDto } from './dto/auth/login-request.dto';
import { LoginResponseDto } from './dto/auth/login-response.dto';
import { BooksPageDto } from './dto/book/books-page-response.dto';
import { LoansPageDto } from './dto/loan/loans-page-response.dto';
import { BookDetailsDto } from './dto/book-details/book-details.dto';

type ClientResponse<T> = {
  success: boolean;
  data: T;
  status: number;
};

export class LibraryClient {
  private client: AxiosInstance;

  constructor() {
    this.client = axios.create({
      baseURL: 'http://localhost:8080/api',
    });
  }

  public async login(
    data: LoginRequestDto,
  ): Promise<ClientResponse<LoginResponseDto | null>> {
    try {
      const response: AxiosResponse<LoginResponseDto> = await this.client.post(
        '/auth/login',
        data,
      );

      this.client.defaults.headers.common['Authorization'] =
        `Bearer ${response.data.token}`;

      return {
        success: true,
        data: response.data,
        status: response.status,
      };
    } catch (error) {
      const axiosError = error as AxiosError<Error>;

      return {
        success: false,
        data: null,
        status: axiosError.response?.status || 0,
      };
    }
  }

  public async getBooks(
    page: number,
  ): Promise<ClientResponse<BooksPageDto | null>> {
    try {
      const url = `/books?page=${page}`;
      const response = await this.client.get(url);

      return {
        success: true,
        data: response.data,
        status: response.status,
      };
    } catch (error) {
      const axiosError = error as AxiosError<Error>;

      return {
        success: false,
        data: null,
        status: axiosError.response?.status || 0,
      };
    }
  }

  public async getLoans(
    page: number,
  ): Promise<ClientResponse<LoansPageDto | null>> {
    try {
      const url = `/loans?page=${page}`;
      const response = await this.client.get(url);

      return {
        success: true,
        data: response.data,
        status: response.status,
      };
    } catch (error) {
      const axiosError = error as AxiosError<Error>;

      return {
        success: false,
        data: null,
        status: axiosError.response?.status || 0,
      };
    }
  }

  public async getBookDetailsByTitle(
    title: string,
  ): Promise<ClientResponse<BookDetailsDto | null>> {
    try {
      const url = `/books/details/title/${title}`;
      const response = await this.client.get(url);

      return {
        success: true,
        data: response.data,
        status: response.status,
      };
    } catch (error) {
      const axiosError = error as AxiosError<Error>;

      return {
        success: false,
        data: null,
        status: axiosError.response?.status || 0,
      };
    }
  }

  public async getBookDetailsByIsbn(
    isbn: string,
  ): Promise<ClientResponse<BookDetailsDto | null>> {
    try {
      const url = `/books/details/isbn/${isbn}`;
      const response = await this.client.get(url);

      return {
        success: true,
        data: response.data,
        status: response.status,
      };
    } catch (error) {
      const axiosError = error as AxiosError<Error>;

      return {
        success: false,
        data: null,
        status: axiosError.response?.status || 0,
      };
    }
  }
}
