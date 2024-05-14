import { List, Container, Grid } from '@mui/material';
import BookListItem from './BookListItem';
import './BookList.css';

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

function BookList({ books }: BookListProps) {
  return (
    <>
      <Container maxWidth="lg">
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <List className="BookList">
              {mockBooks.map((book) => (
                <div key={book.id} className="BookListItem">
                  <BookListItem key={book.id} book={book} />
                </div>
              ))}
            </List>
          </Grid>
        </Grid>
      </Container>
    </>
  );
}

export default BookList;
