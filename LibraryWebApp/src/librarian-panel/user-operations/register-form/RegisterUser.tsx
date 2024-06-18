import { useNavigate } from 'react-router-dom';
import { useApi } from '../../../api/ApiProvider';
import { useCallback, useMemo } from 'react';
import * as yup from 'yup';
import { Formik } from 'formik';
import { Box, Button, TextField, Typography } from '@mui/material';
import { useTranslation } from 'react-i18next';
import './RegisterUser.css';

type FormValues = {
  username: string;
  password: string;
  role: string;
  email: string;
};

function RegisterUser() {
  const { t } = useTranslation();
  const navigate = useNavigate();
  const apiClient = useApi();

  const onSubmit = useCallback(
    (values: FormValues) => {
      apiClient.registerUser(values).then((response) => {
        if (response.success) {
          alert(t('User has been created successfully.'));
          navigate('/librarian');
        } else {
          alert(t('Invalid user details. Please try again.'));
        }
      });
    },
    [apiClient, navigate, t],
  );

  const validationSchema = useMemo(
    () =>
      yup.object().shape({
        username: yup.string().required(t('Username cannot be empty')),
        password: yup
          .string()
          .required(t('Password cannot be empty'))
          .min(5, t('Password must be at least 5 characters')),
        role: yup
          .string()
          .required(t('Role cannot be empty'))
          .oneOf(
            ['ROLE_ADMIN', 'ROLE_READER'],
            t('Role must be either ROLE_ADMIN or ROLE_READER'),
          ),
        email: yup
          .string()
          .email(t('Email must be a valid email'))
          .required(t('Email cannot be empty')),
      }),
    [t],
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
              style={{
                fontFamily: 'Times New Roman, serif',
                fontWeight: 'bold',
              }}
            >
              {t('Register New User')}
            </Typography>

            <TextField
              id="username"
              label={t('Username')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.username && !!formik.errors.username}
              helperText={formik.touched.username && formik.errors.username}
              margin="normal"
            />
            <TextField
              id="password"
              label={t('Password')}
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
              label={t('Role')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.username && !!formik.errors.username}
              helperText={formik.touched.username && formik.errors.username}
              margin="normal"
            />
            <TextField
              id="email"
              label="Email"
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
                {t('Register')}
              </Button>
            </Box>
          </Box>
        </form>
      )}
    </Formik>
  );
}

export default RegisterUser;
