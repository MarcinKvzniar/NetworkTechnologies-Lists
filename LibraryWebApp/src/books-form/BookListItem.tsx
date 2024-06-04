import { ListItem, ListItemText } from '@mui/material';

interface BookListItemProps {
  book: {
    id: number;
    isbn: string;
    title: string;
    author: string;
    publisher: string;
    yearPublished: number;
    available: boolean;
  };
}

function BookListItem({ book }: BookListItemProps) {
  return (
    <ListItem>
      <ListItemText
        primary={`${book.title}`}
        secondary={
          <>
            <div>
              {' '}
              <b>Author: </b> {book.author}
            </div>
            <div>
              {' '}
              <b>ISBN: </b> {book.isbn}
            </div>
            <div>
              {' '}
              <b>Publisher: </b> {book.publisher}
            </div>
            <div>
              {' '}
              <b>Year Published: </b> {book.yearPublished}
            </div>
            <div className={book.available ? 'Available' : 'NotAvailable'}>
              {book.available ? 'Available' : 'Not Available'}
            </div>
          </>
        }
      />
    </ListItem>
  );
}

export default BookListItem;
