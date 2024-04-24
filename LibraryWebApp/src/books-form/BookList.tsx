import React from 'react';
import { List, Typography } from '@mui/material';
import BookListItem from './BookListItem';
import './Books-list.css';

interface Book {
  id: number;
  isbn: string;
  title: string;
  author: string;
  publisher: string;
  yearPublished: number;
  isAvailable: boolean;
}

interface BookListProps {
  books: Book[];
}

const BookList: React.FC<BookListProps> = ({ books }) => {
  return (
    <>
      {books.length > 0 ? (
        <List className="BookList">
          {books.map((book) => (
            <div key={book.id} className="BookListItem">
              <BookListItem key={book.id} book={book} />
            </div>
          ))}
        </List>
      ) : (
        <Typography variant="subtitle1">No books to display</Typography>
      )}
    </>
  );
};

export default BookList;
