import { List, Container, Grid } from '@mui/material';
import { useEffect, useState } from 'react';
import BookListItem from './BookListItem';
import './BookList.css';
import { useApi } from '../api/ApiProvider';

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

function BookList({ books }: BookListProps) {
  const [bookData, setBookData] = useState<Book[]>([]);
  const apiClient = useApi();

  useEffect(() => {
    apiClient.getBooks().then((response) => {
      if (response.success && response.data) {
        setBookData(response.data);
      } else {
        console.error('No books found');
      }
    });
  }, [apiClient]);

  return (
    <Container maxWidth="lg">
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <List className="BookList">
            {bookData.map((book) => (
              <div key={book.id} className="BookListItem">
                <BookListItem key={book.id} book={book} />
              </div>
            ))}
          </List>
        </Grid>
      </Grid>
    </Container>
  );
}

export default BookList;
