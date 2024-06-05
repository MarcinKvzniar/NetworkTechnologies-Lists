import { useNavigate } from 'react-router-dom';
import { useApi } from '../../../api/ApiProvider';
import { useCallback, useMemo } from 'react';
import * as yup from 'yup';
import { Formik } from 'formik';
import { Box, Button, TextField, Typography } from '@mui/material';

type FormValues = {
  username: string;
  password: string;
  role: string;
  email: string;
};

function RegisterUser() {
  const navigate = useNavigate();
  const apiClient = useApi();

  const onSubmit = useCallback(
    (values: FormValues) => {
      apiClient.registerUser(values).then((response) => {
        if (response.success) {
          alert('User has been created successfully.');
          navigate('/librarian');
        } else {
          alert('Invalid user details. Please try again.');
        }
      });
    },
    [apiClient, navigate],
  );

  const validationSchema = useMemo(
    () =>
      yup.object().shape({
        username: yup.string().required('Username cannot be empty'),
        password: yup
          .string()
          .required('Password cannot be empty')
          .min(5, 'Password must be at least 5 characters'),
        role: yup
          .string()
          .required('Role cannot be empty')
          .oneOf(
            ['ROLE_ADMIN', 'ROLE_READER'],
            'Role must be either ROLE_ADMIN or ROLE_READER',
          ),
        email: yup
          .string()
          .email('Email must be a valid email')
          .required('Email cannot be empty'),
      }),
    [],
  );

  return (
    <Formik
      initialValues={{ username: '', password: '', role: '', email: '' }}
      onSubmit={onSubmit}
      validationSchema={validationSchema}
      validateOnBlur
      validateOnChange
    >
      {(formik: any) => (
        <form
          id="registerForm"
          className="Register-form"
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
              Register New User
            </Typography>

            <TextField
              id="username"
              label="username"
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.username && !!formik.errors.username}
              helperText={formik.touched.username && formik.errors.username}
              margin="normal"
            />
            <TextField
              id="password"
              label="password"
              variant="standard"
              type="password"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.username && !!formik.errors.username}
              helperText={formik.touched.username && formik.errors.username}
              margin="normal"
            />
            <TextField
              id="role"
              label="role"
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.username && !!formik.errors.username}
              helperText={formik.touched.username && formik.errors.username}
              margin="normal"
            />
            <TextField
              id="email"
              label="email"
              variant="standard"
              type="email"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.username && !!formik.errors.username}
              helperText={formik.touched.username && formik.errors.username}
              margin="normal"
            />
            <Box margin="normal" marginTop={5}>
              <Button
                variant="contained"
                type="submit"
                disabled={!(formik.isValid && formik.dirty)}
              >
                {' '}
                Register{' '}
              </Button>
            </Box>
          </Box>
        </form>
      )}
    </Formik>
  );
}

export default RegisterUser;
