import { Button, TextField, Typography } from '@mui/material';
import './LoginForm.css';
import LoginIcon from '@mui/icons-material/Login';
import { Formik } from 'formik';
import { useCallback, useMemo } from 'react';
import * as yup from 'yup';
import { useNavigate } from 'react-router-dom';
import { useApi } from '../api/ApiProvider';

type FormValues = {
  username: string;
  password: string;
};

function LoginForm() {
  const navigate = useNavigate();
  const apiClient = useApi();

  const onSubmit = useCallback(
    (values: FormValues, formik: any) => {
      apiClient.login(values).then((response) => {
        if (response.success) {
          navigate('/home');
        } else {
          formik.setFieldError({ username: 'Invalid username or password' });
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
      }),
    [],
  );

  return (
    <Formik
      initialValues={{ username: '', password: '' }}
      onSubmit={onSubmit}
      validationSchema={validationSchema}
      validateOnBlur
      validateOnChange
    >
      {(formik: any) => (
        <form
          id="loginForm"
          className="Login-form"
          onSubmit={formik.handleSubmit}
        >
          <Typography
            component="h1"
            variant="h2"
            align="center"
            color="DarkBlue"
            gutterBottom
            className="header"
          >
            Library Application
          </Typography>

          <TextField
            id="username"
            label="username"
            variant="standard"
            onBlur={formik.handleBlur}
            onChange={formik.handleChange}
            error={formik.touched.username && !!formik.errors.username}
            helperText={formik.touched.username && formik.errors.username}
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
          />
          <Button
            variant="contained"
            startIcon={<LoginIcon />}
            type="submit"
            disabled={!(formik.isValid && formik.dirty)}
            sx={{ backgroundColor: 'DarkBlue' }}
          >
            {' '}
            Sign In{' '}
          </Button>
          <Typography
            variant="body2"
            color="darkBlue"
            align="center"
            className="footer"
          >
            {'If you do not have an account, please contact us at '}
            <a href="mailto:library@gmail.com">library@gmail.com</a>
            {' or find our library, in order to register as a reader.'}
          </Typography>
        </form>
      )}
    </Formik>
  );
}

export default LoginForm;
