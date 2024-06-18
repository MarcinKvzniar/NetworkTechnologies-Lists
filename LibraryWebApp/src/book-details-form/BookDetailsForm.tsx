import { useState } from 'react';
import {
  TextField,
  Card,
  CardContent,
  CardMedia,
  Typography,
  IconButton,
  Grid,
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import { useApi } from '../api/ApiProvider';
import { BookDetailsDto } from '../api/dto/book-details/book-details.dto';
import { BookDetailsItemDto } from '../api/dto/book-details/book-details-item.dto';
import { useTranslation } from 'react-i18next';

function BookDetailsForm() {
  const [searchByTitle, setSearchByTitle] = useState('');
  const [searchByIsbn, setSearchByIsbn] = useState('');
  const [bookDetailsData, setBookDetailsData] = useState<BookDetailsDto | null>(
    null,
  );
  const { t } = useTranslation();
  const apiClient = useApi();

  const handleSearchByTitle = async () => {
    if (searchByTitle) {
      const response = await apiClient.getBookDetailsByTitle(searchByTitle);
      if (response) {
        setBookDetailsData(response.data);
      }
    }
  };

  const handleSearchByIsbn = async () => {
    if (searchByIsbn) {
      const response = await apiClient.getBookDetailsByIsbn(searchByIsbn);
      if (response) {
        setBookDetailsData(response.data);
      }
    }
  };

  return (
    <Grid
      container
      spacing={0}
      direction="column"
      marginTop={2}
      alignItems="center"
      style={{ minHeight: '100vh' }}
    >
      <Grid container justifyContent="center">
        <Grid item>
          <TextField
            value={searchByTitle}
            onChange={(e) => setSearchByTitle(e.target.value)}
            label={t('Search for a book by title')}
            variant="outlined"
            style={{ marginRight: '5px' }}
          />
          <IconButton onClick={handleSearchByTitle}>
            <SearchIcon />
          </IconButton>
        </Grid>
        <Grid item>
          <TextField
            value={searchByIsbn}
            onChange={(e) => setSearchByIsbn(e.target.value)}
            label={t('Search for a book by ISBN')}
            variant="outlined"
            style={{ marginRight: '5px' }}
          />
          <IconButton onClick={handleSearchByIsbn}>
            <SearchIcon />
          </IconButton>
        </Grid>
      </Grid>
      {bookDetailsData &&
        bookDetailsData.items.map((bookDetail: BookDetailsItemDto) => (
          <Card sx={{ maxWidth: 345, m: 2 }} key={bookDetail.volumeInfo.title}>
            <CardMedia
              component="img"
              height="140"
              image={bookDetail?.volumeInfo?.imageLinks?.thumbnail}
              alt={bookDetail?.volumeInfo?.title}
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="div">
                {bookDetail?.volumeInfo?.title}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                {bookDetail?.volumeInfo?.description}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                <b>{t('Authors')}: </b>{' '}
                {bookDetail?.volumeInfo?.authors?.join(', ')}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                <b>{t('Published Date')}: </b>{' '}
                {bookDetail?.volumeInfo?.publishedDate}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                <b>{t('Categories')}: </b>{' '}
                {bookDetail?.volumeInfo?.categories?.join(', ')}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                <b>{t('Page Count')}: </b> {bookDetail?.volumeInfo?.pageCount}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                <b>{t('Language')}: </b> {bookDetail?.volumeInfo?.language}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                <b>Ebook: </b>{' '}
                {bookDetail?.saleInfo?.ebook ? t('Yes') : t('No')}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                <b>{t('Availability')}: </b>{' '}
                {bookDetailsData?.isAvailable ? t('Yes') : t('No')}
              </Typography>
            </CardContent>
          </Card>
        ))}
    </Grid>
  );
}

export default BookDetailsForm;
