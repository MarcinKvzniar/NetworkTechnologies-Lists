import { List, Container, Grid, Button, Box } from '@mui/material';
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
  available: boolean;
}

interface BookListProps {
  books: Book[];
}

function BookList({ books }: BookListProps) {
  const [bookData, setBookData] = useState<Book[]>([]);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const apiClient = useApi();

  useEffect(() => {
    apiClient.getBooks(page).then((response) => {
      if (response.success && response.data) {
        setBookData(response.data.books);
        setHasMore(response.data.hasMore);
      } else {
        setBookData([]);
      }
    });
  }, [apiClient, page]);

  const handleNext = () => {
    if (hasMore) {
      setPage((prevPage) => prevPage + 1);
    }
  };

  const handlePrevious = () => {
    setPage((prevPage) => Math.max(prevPage - 1, 0));
  };

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
          <Box display="flex" justifyContent="center" mt={2} mb={1}>
            <Button
              variant="contained"
              color="primary"
              sx={{ backgroundColor: 'DarkBlue' }}
              onClick={handlePrevious}
              style={{ marginRight: '10px' }}
              disabled={page === 0}
            >
              Previous
            </Button>
            <Button
              variant="contained"
              color="primary"
              sx={{ backgroundColor: 'DarkBlue' }}
              onClick={handleNext}
              disabled={!hasMore}
            >
              Next
            </Button>
          </Box>
        </Grid>
      </Grid>
    </Container>
  );
}

export default BookList;
