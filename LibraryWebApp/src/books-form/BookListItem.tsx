import { ListItem, ListItemText } from '@mui/material';
import { useTranslation } from 'react-i18next';

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
  const { t } = useTranslation();

  return (
    <ListItem>
      <ListItemText
        primary={`${book.title}`}
        secondary={
          <>
            <div>
              {' '}
              <b>{t('Author')}: </b> {book.author}
            </div>
            <div>
              {' '}
              <b>ISBN: </b> {book.isbn}
            </div>
            <div>
              {' '}
              <b>{t('Publisher')}: </b> {book.publisher}
            </div>
            <div>
              {' '}
              <b>{t('Year Published')}: </b> {book.yearPublished}
            </div>
            <div className={book.available ? 'Available' : 'NotAvailable'}>
              {book.available ? t('Available') : t('Not Available')}
            </div>
          </>
        }
      />
    </ListItem>
  );
}

export default BookListItem;
