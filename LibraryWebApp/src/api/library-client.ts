import axios, { AxiosError, AxiosInstance, AxiosResponse } from 'axios';
import { LoginRequestDto } from './dto/auth/login-request.dto';
import { LoginResponseDto } from './dto/auth/login-response.dto';
import { BookResponseDto } from './dto/book/book-response.dto';

type ClientResponse<T> = {
  success: boolean;
  data: T;
  status: number;
};

export class LibraryClient {
  private baseUrl = 'http://localhost:8080/api';
  private client: AxiosInstance;

  constructor() {
    this.client = axios.create({
      baseURL: this.baseUrl,
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

  public async getBooks(): Promise<ClientResponse<BookResponseDto | null>> {
    try {
      const response: AxiosResponse<BookResponseDto> =
        await this.client.get('/books');

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
