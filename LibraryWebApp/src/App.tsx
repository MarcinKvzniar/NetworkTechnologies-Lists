import React from 'react';
import { Container, Grid } from '@mui/material';
import './App.css';
import LoginForm from './login-form/Login-form';
import BookList from './books-form/BookList';

const mockBooks = [
  {
    id: 1,
    isbn: '9780061120084',
    title: 'To Kill a Mockingbird',
    author: 'Harper Lee',
    publisher: 'Harper Perennial Modern Classics',
    yearPublished: 1960,
    isAvailable: true,
  },
  {
    id: 2,
    isbn: '9781982137274',
    title: '1984',
    author: 'George Orwell',
    publisher: 'Signet Classic',
    yearPublished: 1949,
    isAvailable: false,
  },
  {
    id: 3,
    isbn: '9780143111597',
    title: 'The Catcher in the Rye',
    author: 'J.D. Salinger',
    publisher: 'Back Bay Books',
    yearPublished: 1951,
    isAvailable: true,
  },
  {
    id: 4,
    isbn: '9780446310789',
    title: 'To Kill a Mockingbird',
    author: 'Harper Lee',
    publisher: 'Warner Books',
    yearPublished: 1960,
    isAvailable: true,
  },
  {
    id: 5,
    isbn: '9780743273565',
    title: 'The Great Gatsby',
    author: 'F. Scott Fitzgerald',
    publisher: 'Scribner',
    yearPublished: 1925,
    isAvailable: false,
  },
];

function App() {
  return (
    <Container maxWidth="lg">
      <Grid container spacing={3}>
        {/* Login Form Section */}
        <Grid item xs={12} md={6}>
          <LoginForm />
        </Grid>
        {/* Book List Section */}
        <Grid item xs={12} md={6}>
          <BookList books={mockBooks} />
        </Grid>
      </Grid>
    </Container>
  );
}

export default App;
