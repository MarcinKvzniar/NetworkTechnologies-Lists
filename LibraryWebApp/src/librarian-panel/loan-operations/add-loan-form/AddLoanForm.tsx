import { useNavigate } from 'react-router-dom';
import { useApi } from '../../../api/ApiProvider';
import { useCallback, useMemo } from 'react';
import * as yup from 'yup';
import { Box, Button, TextField, Typography } from '@mui/material';
import { Formik } from 'formik';
import { useTranslation } from 'react-i18next';
import './AddLoanForm.css';

type FormValues = {
  dueDate: string;
  userId: number;
  bookId: number;
};

function CreateLoan() {
  const navigate = useNavigate();
  const { t } = useTranslation();
  const apiClient = useApi();

  const onSubmit = useCallback(
    (values: FormValues) => {
      apiClient.createLoan(values).then((response) => {
        if (response.success) {
          alert(
            t('Book with id ') +
              `${values.bookId}` +
              t('has been successfully loaned to user with id ') +
              `${values.userId}.`,
          );
          navigate('/librarian');
        } else {
          alert(t('Invalid details. Please try again.'));
        }
      });
    },
    [apiClient, navigate, t],
  );

  const validationSchema = useMemo(
    () =>
      yup.object().shape({
        dueDate: yup.string().required(t('Due date cannot be empty')),
        userId: yup.number().required(t('User id cannot be empty')),
        bookId: yup.number().required(t('Book id cannot be empty')),
      }),
    [t],
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
              style={{
                fontFamily: 'Times New Roman, serif',
                fontWeight: 'bold',
              }}
            >
              {t('Create New Loan')}
            </Typography>

            <TextField
              id="dueDate"
              label={t('Due Date (yyyy-mm-dd)')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.dueDate && !!formik.errors.dueDate}
              helperText={formik.touched.dueDate && formik.errors.dueDate}
              margin="normal"
            />
            <TextField
              id="userId"
              label={t('User ID')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.userId && !!formik.errors.userId}
              helperText={formik.touched.userId && formik.errors.userId}
              margin="normal"
            />
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
                {t('Create a loan')}
              </Button>
            </Box>
          </Box>
        </form>
      )}
    </Formik>
  );
}

export default CreateLoan;
