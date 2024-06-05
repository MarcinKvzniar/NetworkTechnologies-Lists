import './App.css';
import LoginForm from './login-form/LoginForm';
import BookList from './books-form/BookList';
import HomePage from './home-page/HomePage';
import LoanList from './loans-form/LoanList';
import BookDetailsForm from './book-details-form/BookDetailsForm';
import LibrarianPanel from './librarian-panel/LibrarianPanel';
import { Navigate, Routes, Route } from 'react-router-dom';
import ApiProvider from './api/ApiProvider';
import RegisterUser from './librarian-panel/user-operations/register-form/RegisterUser';
import CreateLoan from './librarian-panel/add-loan-form/AddLoanForm';
import CreateBook from './librarian-panel/add-book-form/AddBookForm';

function App() {
  return (
    <ApiProvider>
      <Routes>
        <Route path="/login" element={<LoginForm />} />
        <Route path="/home" element={<HomePage />}>
          <Route path="books" element={<BookList books={[]} />} />
          <Route path="loans" element={<LoanList loans={[]} />} />
          <Route path="catalog" element={<BookDetailsForm />} />
        </Route>
        <Route path="/librarian" element={<LibrarianPanel />} />
        <Route path="/librarian/register" element={<RegisterUser />} />
        <Route path="/librarian/add-loan" element={<CreateLoan />} />
        <Route path="/librarian/add-book" element={<CreateBook />} />
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="*" element={<h1>404</h1>} />
      </Routes>
    </ApiProvider>
  );
}

export default App;
