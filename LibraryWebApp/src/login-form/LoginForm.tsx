import {
  Button,
  Grid,
  IconButton,
  TextField,
  Tooltip,
  Typography,
} from '@mui/material';
import './LoginForm.css';
import LoginIcon from '@mui/icons-material/Login';
import { Formik } from 'formik';
import { useCallback, useMemo, useState } from 'react';
import * as yup from 'yup';
import i18n from '../i18n';
import { useNavigate } from 'react-router-dom';
import { useApi } from '../api/ApiProvider';
import { useTranslation } from 'react-i18next';
import PolishFlag from '../utils/pl-flag.webp';
import EnglishFlag from '../utils/en-us-flag.jpg';

type FormValues = {
  username: string;
  password: string;
};

function LoginForm() {
  const navigate = useNavigate();
  const apiClient = useApi();
  const { t } = useTranslation();
  const [language, setLanguage] = useState(i18n.language);

  const onSubmit = useCallback(
    (values: FormValues, formik: any) => {
      apiClient.login(values).then((response) => {
        if (response.success) {
          navigate('/home');
        } else {
          formik.setFieldError('username', t('Invalid username or password'));
        }
      });
    },
    [apiClient, navigate, t],
  );

  const validationSchema = useMemo(
    () =>
      yup.object().shape({
        username: yup
          .string()
          .required(t('Username cannot be empty'))
          .matches(/^[a-zA-Z0-9]+$/, t('Username must be alphanumeric'))
          .min(5, t('Username must be at least 5 characters')),
        password: yup
          .string()
          .required(t('Password cannot be empty'))
          .min(5, t('Password must be at least 5 characters')),
      }),
    [t],
  );

  const handleLanguageChange = () => {
    const newLanguage = language === 'en' ? 'pl' : 'en';
    i18n.changeLanguage(newLanguage);
    setLanguage(newLanguage);
  };

  return (
    <Grid container direction="column" alignItems="center" mt={5}>
      <Tooltip title={t('Change language')}>
        <IconButton
          onClick={handleLanguageChange}
          sx={{ padding: 0, width: '30px', height: '20px' }}
        >
          <img
            src={language === 'en' ? PolishFlag : EnglishFlag}
            alt="flag"
            style={{ width: '100%', height: '100%' }}
          />
        </IconButton>
      </Tooltip>
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
              {t('Library Application')}
            </Typography>

            <TextField
              id="username"
              label={t('Username')}
              variant="standard"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              error={formik.touched.username && !!formik.errors.username}
              helperText={formik.touched.username && formik.errors.username}
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
            />
            <Button
              variant="contained"
              startIcon={<LoginIcon />}
              type="submit"
              disabled={!(formik.isValid && formik.dirty)}
              sx={{ backgroundColor: 'DarkBlue' }}
            >
              {t('Sign up')}
            </Button>
            <Typography
              variant="body2"
              color="darkBlue"
              align="center"
              className="footer"
            >
              {t('If you do not have an account, please contact us at ')}
              <a href="mailto:library@gmail.com">library@gmail.com</a>
              {t(' or find our library, in order to register as a reader.')}
            </Typography>
          </form>
        )}
      </Formik>
    </Grid>
  );
}

export default LoginForm;
