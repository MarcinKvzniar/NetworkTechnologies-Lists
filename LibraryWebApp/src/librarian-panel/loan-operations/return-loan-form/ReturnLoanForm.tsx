import { useNavigate } from 'react-router-dom';
import { useApi } from '../../../api/ApiProvider';
import { useCallback, useMemo } from 'react';
import * as yup from 'yup';
import { Formik } from 'formik';
import { Box, Button, TextField, Typography } from '@mui/material';

type FormValues = {
  loanId: number;
};

function ReturnLoan() {
  const navigate = useNavigate();
  const apiClient = useApi();

  const onSubmit = useCallback(
    (values: FormValues) => {
      apiClient.returnLoan(values.loanId).then((response) => {
        if (response.success) {
          alert(
            `Loan with id ${values.loanId} has been successfully returned.`,
          );
          navigate('/librarian');
        } else {
          alert('Invalid loan id. Please try again.');
        }
      });
    },
    [apiClient, navigate],
  );

  const validationSchema = useMemo(
    () =>
      yup.object().shape({
        loanId: yup.number().required('Loan id cannot be empty'),
      }),
    [],
  );

  return (
    <Formik
      initialValues={{ loanId: 0 }}
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
              className="header"
            >
              Delete Book
            </Typography>

            <TextField
              id="bookId"
              label="Book ID"
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
                Return a book
              </Button>
            </Box>
          </Box>
        </form>
      )}
    </Formik>
  );
}

export default ReturnLoan;
