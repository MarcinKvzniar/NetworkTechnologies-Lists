import './App.css';
import LoginForm from './login-form/LoginForm';
import BookList from './books-form/BookList';
import HomePage from './home-page/HomePage';
import LoanList from './loans-form/LoanList';
import { Navigate, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginForm />} />
      <Route path="/home" element={<HomePage />}>
        <Route path="books" element={<BookList books={[]} />} />
        <Route path="loans" element={<LoanList loans={[]} />} />
      </Route>
      <Route path="/" element={<Navigate to="/login" />} />
      <Route path="*" element={<h1>404</h1>} />
    </Routes>
  );
}

export default App;
