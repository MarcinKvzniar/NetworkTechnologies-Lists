import { useNavigate } from 'react-router-dom';
import { useApi } from '../../../api/ApiProvider';
import { useCallback, useMemo } from 'react';
import * as yup from 'yup';
import { Formik } from 'formik';
import { Box, Button, TextField, Typography } from '@mui/material';
import './AddBookForm.css';
import { useTranslation } from 'react-i18next';

type FormValues = {
  isbn: string;
  title: string;
  author: string;
  publisher: string;
  yearPublished: number;
  availableCopies: number;
};

function CreateBook() {
  const navigate = useNavigate();
  const apiClient = useApi();
  const { t } = useTranslation();

  const onSubmit = useCallback(
    (values: FormValues) => {
      apiClient.createBook(values).then((response) => {
        if (response.success) {
          alert(
            t('Book ') +
              `"${values.title}"` +
              t(' has been successfully added to library in') +
              `${values.availableCopies}` +
              t(' copies.'),
          );
          navigate('/librarian');
        } else {
          alert(t('Invalid book details. Please try again.'));
        }
      });
    },
    [apiClient, navigate, t],
  );

  const validationSchema = useMemo(
    () =>
      yup.object().shape({
        isbn: yup.string().required(t('ISBN cannot be empty')),
        title: yup.string().required(t('Title cannot be empty')),
        author: yup.string().required(t('Author cannot be empty')),
        publisher: yup.string(),
        yearPublished: yup.number(),
        availableCopies: yup
          .number()
          .required(t('Number of available copies cannot be empty'))
          .moreThan(0, t('Number of available copies must be greater than 0')),
      }),
    [t],
  );

  return (
    <Formik
      initialValues={{
        isbn: '',
        title: '',
        author: '',
        publisher: '',
        yearPublished: 0,
        availableCopies: 0,
      }}
      onSubmit={onSubmit}
      validationSchema={validationSchema}
      validateOnBlur
      validateOnChange
    >
      {(formik: any) => (
        <form
          id="CreateBookForm"
          className="Create-Book-Form"
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
              {t('Add New Book')}
            </Typography>

            <TextField
              id="isbn"
              label="ISBN"
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.isbn && !!formik.errors.isbn}
              helperText={formik.touched.isbn && formik.errors.isbn}
              margin="normal"
            />
            <TextField
              id="title"
              label={t('Title')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.title && !!formik.errors.title}
              helperText={formik.touched.title && formik.errors.title}
              margin="normal"
            />
            <TextField
              id="author"
              label={t('Author')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.author && !!formik.errors.author}
              helperText={formik.touched.author && formik.errors.author}
              margin="normal"
            />
            <TextField
              id="publisher"
              label={t('Publisher')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.publisher && !!formik.errors.publisher}
              helperText={formik.touched.publisher && formik.errors.publisher}
              margin="normal"
            />
            <TextField
              id="yearPublished"
              label={t('Year Published')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={
                formik.touched.yearPublished && !!formik.errors.yearPublished
              }
              helperText={
                formik.touched.yearPublished && formik.errors.yearPublished
              }
              margin="normal"
            />
            <TextField
              id="availableCopies"
              label={t('Available Copies')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={
                formik.touched.availableCopies &&
                !!formik.errors.availableCopies
              }
              helperText={
                formik.touched.availableCopies && formik.errors.availableCopies
              }
              margin="normal"
            />
            <Box margin="normal" marginTop={5}>
              <Button
                variant="contained"
                type="submit"
                disabled={!(formik.isValid && formik.dirty)}
              >
                {t('Add a book')}
              </Button>
            </Box>
          </Box>
        </form>
      )}
    </Formik>
  );
}

export default CreateBook;
