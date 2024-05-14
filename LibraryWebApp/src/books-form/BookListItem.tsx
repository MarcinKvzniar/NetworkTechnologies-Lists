import { ListItem, ListItemText } from '@mui/material';

interface BookListItemProps {
  book: {
    id: number;
    isbn: string;
    title: string;
    author: string;
    publisher: string;
    yearPublished: number;
    isAvailable: boolean;
  };
}

function BookListItem({ book }: BookListItemProps) {
  return (
    <ListItem>
      <ListItemText
        primary={`${book.title} (${book.yearPublished})`}
        secondary={
          <>
            <div>Author: {book.author}</div>
            <div>ISBN: {book.isbn}</div>
            <div>Publisher: {book.publisher}</div>
            <div className={book.isAvailable ? 'Available' : 'NotAvailable'}>
              {book.isAvailable ? 'Available' : 'Not Available'}
            </div>
          </>
        }
      />
    </ListItem>
  );
}

export default BookListItem;
