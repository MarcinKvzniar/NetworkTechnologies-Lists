import { Button, TextField } from '@mui/material';
import './LoginForm.css';
import LoginIcon from '@mui/icons-material/Login';
import { Formik } from 'formik';
import { useCallback, useMemo } from 'react';
import * as yup from 'yup';
import axios, { AxiosInstance } from 'axios';

type FormValues = {
  username: string;
  password: string;
};

function LoginForm() {
  const submit = useCallback((values: FormValues, formik: any) => {
    const client: AxiosInstance = axios.create({
      baseURL: 'http://localhost:8080/api',
    });

    client.post('/auth/login', values).then((response) => {
      console.log(response);

      if (!response.success) {
        formik.setErrors({ username: 'Invalid username or password' });
      }
    });
  }, []);

  const validationSchema = useMemo(
    () =>
      yup.object({
        username: yup.string().required('Username cannot be empty'),
        password: yup.string().required('Password cannot be empty').min(5),
      }),
    [],
  );

  return (
    <Formik
      initialValues={{ username: '', password: '' }}
      onSubmit={submit}
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
          >
            {' '}
            Sign In{' '}
          </Button>
        </form>
      )}
    </Formik>
  );
}

export default LoginForm;
