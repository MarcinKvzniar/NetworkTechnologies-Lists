import './App.css';
import LoginForm from './login-form/LoginForm';
import BookList from './books-form/BookList';
import HomePage from './home-page/HomePage';
import LoanList from './loans-form/LoanList';
import BookDetailsForm from './book-details-form/BookDetailsForm';
import LibrarianPanel from './librarian-panel/LibrarianPanel';
import { Navigate, Routes, Route } from 'react-router-dom';
import ApiProvider from './api/ApiProvider';
import { useApi } from './api/ApiProvider';
import RegisterUser from './librarian-panel/user-operations/register-form/RegisterUser';
import CreateLoan from './librarian-panel/loan-operations/add-loan-form/AddLoanForm';
import CreateBook from './librarian-panel/book-operations/add-book-form/AddBookForm';
import UserList from './librarian-panel/user-operations/users-form/UserList';
import DeleteBook from './librarian-panel/book-operations/delete-book-form/DeleteBookForm';
import AllLoans from './librarian-panel/loan-operations/all-loans-form/AllLoansForm';
import AccountDetails from './account-details-form/AccountDetails';
import { I18nextProvider } from 'react-i18next';
import i18n from './i18n';
import { ComponentType, PropsWithChildren } from 'react';

function withAuth<P>(Component: ComponentType<P>) {
  return function ProtectedRoute(props: PropsWithChildren<P>) {
    const apiClient = useApi();

    if (!apiClient.isLoggedIn()) {
      return <Navigate to="/login" />;
    }

    return <Component {...props} />;
  };
}

function withAdmin<P>(Component: ComponentType<P>) {
  return function ProtectedRoute(props: PropsWithChildren<P>) {
    const apiClient = useApi();

    if (apiClient.getUserRole() !== 'ROLE_ADMIN') {
      return <Navigate to="/home" />;
    }

    return <Component {...props} />;
  };
}

function App() {
  const AuthenticatedHomePage = withAuth(HomePage);
  const AuthenticatedBookList = withAuth(BookList);
  const AuthenticatedLoanList = withAuth(LoanList);
  const AuthenticatedBookDetailsForm = withAuth(BookDetailsForm);
  const AuthenticatedAccountDetails = withAuth(AccountDetails);

  const AdminLibrarianPanel = withAdmin(LibrarianPanel);
  const AdminRegisterUser = withAdmin(RegisterUser);
  const AdminUserList = withAdmin(UserList);
  const AdminCreateLoan = withAdmin(CreateLoan);
  const AdminAllLoans = withAdmin(AllLoans);
  const AdminCreateBook = withAdmin(CreateBook);
  const AdminDeleteBook = withAdmin(DeleteBook);

  return (
    <I18nextProvider i18n={i18n}>
      <ApiProvider>
        <Routes>
          <Route path="/login" element={<LoginForm />} />
          <Route path="/home" element={<AuthenticatedHomePage />}>
            <Route
              path="books"
              element={<AuthenticatedBookList books={[]} />}
            />
            <Route
              path="loans"
              element={<AuthenticatedLoanList loans={[]} />}
            />
            <Route path="catalog" element={<AuthenticatedBookDetailsForm />} />
          </Route>
          <Route path="/account" element={<AuthenticatedAccountDetails />} />

          <Route path="/librarian" element={<AdminLibrarianPanel />} />
          <Route path="/librarian/register" element={<AdminRegisterUser />} />
          <Route
            path="/librarian/users"
            element={<AdminUserList users={[]} />}
          />
          <Route path="/librarian/add-loan" element={<AdminCreateLoan />} />
          <Route
            path="/librarian/loans"
            element={<AdminAllLoans loans={[]} />}
          />
          <Route path="/librarian/add-book" element={<AdminCreateBook />} />
          <Route path="/librarian/delete-book" element={<AdminDeleteBook />} />

          <Route path="/" element={<Navigate to="/login" />} />
          <Route path="*" element={<h1>404</h1>} />
        </Routes>
      </ApiProvider>
    </I18nextProvider>
  );
}

export default App;
