import './App.css';
import LoginForm from './login-form/LoginForm';
import BookList from './books-form/BookList';
import HomePage from './home-page/HomePage';
import LoanList from './loans-form/LoanList';
import { Navigate, Routes, Route } from 'react-router-dom';
import ApiProvider from './api/ApiProvider';

function App() {
  return (
    <ApiProvider>
      <Routes>
        <Route path="/login" element={<LoginForm />} />
        <Route path="/home" element={<HomePage />}>
          <Route path="books" element={<BookList books={[]} />} />
          <Route path="loans" element={<LoanList loans={[]} />} />
        </Route>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="*" element={<h1>404</h1>} />
      </Routes>
    </ApiProvider>
  );
}

export default App;
