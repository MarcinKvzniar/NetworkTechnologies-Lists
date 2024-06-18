import { useCallback, useMemo } from 'react';
import { useApi } from '../../../api/ApiProvider';
import { useNavigate } from 'react-router-dom';
import * as yup from 'yup';
import { Formik } from 'formik';
import { Box, Button, TextField, Typography } from '@mui/material';
import { useTranslation } from 'react-i18next';
import './DeleteBookForm.css';

type FormValues = {
  bookId: number;
};

function DeleteBook() {
  const navigate = useNavigate();
  const { t } = useTranslation();
  const apiClient = useApi();

  const onSubmit = useCallback(
    (values: FormValues) => {
      apiClient.deleteBook(values.bookId).then((response) => {
        if (response.success) {
          alert(
            t('Book with id ') +
              `${values.bookId}` +
              t(' has been successfully deleted from the library.'),
          );
          navigate('/librarian');
        } else {
          alert(t('Invalid book id. Please try again.'));
        }
      });
    },
    [apiClient, navigate, t],
  );

  const validationSchema = useMemo(
    () =>
      yup.object().shape({
        bookId: yup.number().required(t('Book id cannot be empty')),
      }),
    [t],
  );

  return (
    <Formik
      initialValues={{ bookId: 0 }}
      onSubmit={onSubmit}
      validationSchema={validationSchema}
      validateOnBlur
      validateOnChange
    >
      {(formik: any) => (
        <form
          id="DeleteBookForm"
          className="Delete-Book-form"
          onSubmit={formik.handleSubmit}
        >
          <Box display="flex" flexDirection="column" alignItems="center">
            <Typography
              component="h1"
              variant="h2"
              align="center"
              color="Black"
              gutterBottom
              style={{
                fontFamily: 'Times New Roman, serif',
                fontWeight: 'bold',
              }}
            >
              {t('Delete Book')}
            </Typography>

            <TextField
              id="bookId"
              label={t('Book ID')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.bookId && !!formik.errors.bookId}
              helperText={formik.touched.bookId && formik.errors.bookId}
              margin="normal"
            />
            <Box margin="normal" marginTop={5}>
              <Button
                variant="contained"
                type="submit"
                disabled={!(formik.isValid && formik.dirty)}
              >
                {t('Delete Book')}
              </Button>
            </Box>
          </Box>
        </form>
      )}
    </Formik>
  );
}

export default DeleteBook;
