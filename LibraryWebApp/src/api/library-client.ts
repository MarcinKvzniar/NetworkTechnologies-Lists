import axios, { AxiosError, AxiosInstance, AxiosResponse } from 'axios';
import { LoginRequestDto } from './dto/auth/login-request.dto';
import { LoginResponseDto } from './dto/auth/login-response.dto';
import { BooksPageDto } from './dto/book/books-page-response.dto';
import { LoansPageDto } from './dto/loan/loans-page-response.dto';
import { BookDetailsDto } from './dto/book-details/book-details.dto';
import { jwtDecode, JwtPayload } from 'jwt-decode';
import { RegisterRequestDto } from './dto/auth/register-request.dto';
import { RegisterResponseDto } from './dto/auth/register-response.dto';
import { CreateLoanRequestDto } from './dto/loan/create-loan-request.dto';
import { CreateLoanResponseDto } from './dto/loan/create-loan-response.dto';
import { CreateBookRequestDto } from './dto/book/create-book-request.dto';
import { CreateBookResponseDto } from './dto/book/create-book-response.dto';
import { UsersPageDto } from './dto/user/users-page-response.dto';
import Cookies from 'universal-cookie';
import { UserDto } from './dto/user/user.dto';
import { PatchUserDto } from './dto/user/patch-user.dto';
import { PatchUserResponseDto } from './dto/user/patch-user-response.dto';

type ClientResponse<T> = {
  success: boolean;
  data: T;
  status: number;
};

interface MyJwtPayLoad extends JwtPayload {
  role?: string;
}

export class LibraryClient {
  private client: AxiosInstance;
  private cookies = new Cookies();

  constructor() {
    this.client = axios.create({
      baseURL: 'http://localhost:8080/api',
    });

    this.client.interceptors.request.use((config) => {
      const token = this.cookies.get('token');

      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }

      return config;
    });

    this.client.interceptors.response.use(
      (response) => response,
      async (error) => {
        const originalRequest = error.config;

        if (error.response.status === 401 && !originalRequest._retry) {
          originalRequest._retry = true;

          await this.refreshToken();

          return this.client(originalRequest);
        }

        return Promise.reject(error);
      },
    );

    this.client.interceptors.response.use(
      (response) => response,
      async (error) => {
        if (error.response.status === 401) {
          await this.refreshToken();

          const originalRequest = error.config;
          return this.client(originalRequest);
        }

        throw error;
      },
    );
  }

  public isLoggedIn(): boolean {
    const token = this.cookies.get('token');
    return Boolean(token);
  }

  public getUserRole(): string {
    const token = this.cookies.get('token');
    if (token) {
      const decoded = jwtDecode<MyJwtPayLoad>(token);
      if (decoded.role) {
        return decoded.role;
      }
    }
    return '';
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

      const decoded = jwtDecode<MyJwtPayLoad>(response.data.token);

      if (decoded.exp) {
        this.cookies.set('token', response.data.token, {
          expires: new Date(decoded.exp * 1000),
        });

        this.cookies.set('refreshToken', response.data.refreshToken);
      }

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

  public async refreshToken(): Promise<void> {
    const refreshToken = this.cookies.get('refreshToken');

    if (refreshToken) {
      const response = await this.client.post('/auth/refresh', {
        refreshToken: refreshToken,
      });

      this.client.defaults.headers.common['Authorization'] =
        `Bearer ${response.data.token}`;

      const decoded = jwtDecode<MyJwtPayLoad>(response.data.token);

      if (decoded.exp) {
        this.cookies.set('token', response.data.token, {
          expires: new Date(decoded.exp * 1000),
        });

        this.cookies.set('refreshToken', response.data.refreshToken);
      }
    }
  }

  public signOut(): void {
    this.cookies.remove('token');
    this.client.defaults.headers.common['Authorization'] = '';
  }

  public async registerUser(
    data: RegisterRequestDto,
  ): Promise<ClientResponse<RegisterResponseDto | null>> {
    try {
      const response: AxiosResponse<RegisterResponseDto> =
        await this.client.post('/auth/register', data);

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

  public async getUsers(
    page: number,
  ): Promise<ClientResponse<UsersPageDto | null>> {
    try {
      const url = `/users?page=${page}`;
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

  public async createBook(
    data: CreateBookRequestDto,
  ): Promise<ClientResponse<CreateBookResponseDto | null>> {
    try {
      const response: AxiosResponse<CreateBookResponseDto> =
        await this.client.post('/books/create', data);

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

  public async deleteBook(id: number): Promise<ClientResponse<null | Error>> {
    try {
      await this.client.delete(`/books/delete/${id}`);

      return {
        success: true,
        data: null,
        status: 200,
      };
    } catch (error) {
      const axiosError = error as AxiosError<Error>;

      return {
        success: false,
        data: axiosError,
        status: axiosError.response?.status || 0,
      };
    }
  }

  public async getLoans(
    page: number,
    userId: number,
  ): Promise<ClientResponse<LoansPageDto | null>> {
    try {
      const url = `/loans?userId=${userId}&page=${page}`;
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

  public async getAllLoans(
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

  public async createLoan(
    data: CreateLoanRequestDto,
  ): Promise<ClientResponse<CreateLoanResponseDto | null>> {
    try {
      const response: AxiosResponse<CreateLoanResponseDto> =
        await this.client.post('/loans/create', data);

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

  public async returnLoan(id: number): Promise<ClientResponse<null>> {
    try {
      const response: AxiosResponse<null> = await this.client.patch(
        `/loans/return/${id}`,
      );

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

  public async getCurrentUser(): Promise<ClientResponse<UserDto | null>> {
    try {
      const response: AxiosResponse<UserDto> =
        await this.client.get('/users/me');

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

  public async patchUser(
    data: PatchUserDto,
    id: number,
  ): Promise<ClientResponse<PatchUserResponseDto | null>> {
    try {
      const response: AxiosResponse<PatchUserResponseDto> =
        await this.client.patch(`/users/${id}`, data);

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

  public async getNYTBestsellers(
    retries = 5,
    backoff = 300,
  ): Promise<ClientResponse<Error | any>> {
    try {
      const response = await axios.get(
        `https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=${process.env.REACT_APP_NYT_API_KEY}`,
      );

      return {
        success: true,
        data: response.data.results.books,
        status: response.status,
      };
    } catch (error) {
      const axiosError = error as AxiosError<Error>;

      if (axiosError.response?.status === 429 && retries > 0) {
        await new Promise((resolve) => setTimeout(resolve, backoff));
        return this.getNYTBestsellers(retries - 1, backoff * 2);
      }

      return {
        success: false,
        data: axiosError,
        status: axiosError.response?.status || 0,
      };
    }
  }
}
