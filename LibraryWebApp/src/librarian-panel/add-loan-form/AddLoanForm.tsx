import { useNavigate } from 'react-router-dom';
import { useApi } from '../../api/ApiProvider';
import { useCallback, useMemo } from 'react';
import * as yup from 'yup';
import { Box, Button, TextField, Typography } from '@mui/material';
import { Formik } from 'formik';

type FormValues = {
  dueDate: string;
  userId: number;
  bookId: number;
};

function CreateLoan() {
  const navigate = useNavigate();
  const apiClient = useApi();

  const onSubmit = useCallback(
    (values: FormValues) => {
      apiClient.createLoan(values).then((response) => {
        if (response.success) {
          console.log(response);
          alert(
            `Book with id ${values.bookId} has been successfully loaned to user with id ${values.userId}.`,
          );
          navigate('/librarian');
        } else {
          console.log(response);
          alert('Invalid details. Please try again.');
        }
      });
    },
    [apiClient, navigate],
  );

  const validationSchema = useMemo(
    () =>
      yup.object().shape({
        dueDate: yup.string().required('Due date cannot be empty'),
        userId: yup.number().required('User id cannot be empty'),
        bookId: yup.number().required('Book id cannot be empty'),
      }),
    [],
  );

  return (
    <Formik
      initialValues={{ dueDate: '', userId: 0, bookId: 0 }}
      onSubmit={onSubmit}
      validationSchema={validationSchema}
      validateOnBlur
      validateOnChange
    >
      {(formik: any) => (
        <form
          id="CreateLoanForm"
          className="Create-Loan-form"
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
              Create New Loan
            </Typography>

            <TextField
              id="dueDate"
              label="Due Date (yyyy-mm-dd)"
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.dueDate && !!formik.errors.dueDate}
              helperText={formik.touched.dueDate && formik.errors.dueDate}
              margin="normal"
            />
            <TextField
              id="userId"
              label="User Id"
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.userId && !!formik.errors.userId}
              helperText={formik.touched.userId && formik.errors.userId}
              margin="normal"
            />
            <TextField
              id="bookId"
              label="Book Id"
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
                {' '}
                Create a loan{' '}
              </Button>
            </Box>
          </Box>
        </form>
      )}
    </Formik>
  );
}

export default CreateLoan;
