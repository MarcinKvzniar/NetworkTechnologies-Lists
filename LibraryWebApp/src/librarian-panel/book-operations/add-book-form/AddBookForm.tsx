import { useNavigate } from 'react-router-dom';
import { useApi } from '../../../api/ApiProvider';
import { useCallback, useMemo } from 'react';
import * as yup from 'yup';
import { Formik } from 'formik';
import { Box, Button, TextField, Typography } from '@mui/material';

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

  const onSubmit = useCallback(
    (values: FormValues) => {
      apiClient.createBook(values).then((response) => {
        if (response.success) {
          console.log(response);
          alert(
            `Book "${values.title}" has been successfully added to library in ${values.availableCopies} copies.`,
          );
          navigate('/librarian');
        } else {
          console.log(response);
          alert('Invalid book details. Please try again.');
        }
      });
    },
    [apiClient, navigate],
  );

  const validationSchema = useMemo(
    () =>
      yup.object().shape({
        isbn: yup.string().required('ISBN cannot be empty'),
        title: yup.string().required('Title cannot be empty'),
        author: yup.string().required('Author cannot be empty'),
        publisher: yup.string(),
        yearPublished: yup.number(),
        availableCopies: yup
          .number()
          .required('Number of available copies cannot be empty')
          .moreThan(0, 'Number of available copies must be greater than 0'),
      }),
    [],
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
              className="header"
            >
              Add New Book
            </Typography>

            <TextField
              id="isbn"
              label="isbn"
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.isbn && !!formik.errors.isbn}
              helperText={formik.touched.isbn && formik.errors.isbn}
              margin="normal"
            />
            <TextField
              id="title"
              label="title"
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.title && !!formik.errors.title}
              helperText={formik.touched.title && formik.errors.title}
              margin="normal"
            />
            <TextField
              id="author"
              label="author"
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.author && !!formik.errors.author}
              helperText={formik.touched.author && formik.errors.author}
              margin="normal"
            />
            <TextField
              id="publisher"
              label="publisher"
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.publisher && !!formik.errors.publisher}
              helperText={formik.touched.publisher && formik.errors.publisher}
              margin="normal"
            />
            <TextField
              id="yearPublished"
              label="yearPublished"
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
              label="availableCopies"
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
                {' '}
                Add a book{' '}
              </Button>
            </Box>
          </Box>
        </form>
      )}
    </Formik>
  );
}

export default CreateBook;
