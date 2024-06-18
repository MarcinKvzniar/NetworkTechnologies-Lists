import {
  AppBar,
  Box,
  Button,
  IconButton,
  Toolbar,
  Tooltip,
  Typography,
} from '@mui/material';
import { AccountCircle } from '@mui/icons-material';
import { useNavigate, Link } from 'react-router-dom';
import { useApi } from '../api/ApiProvider';
import { useTranslation } from 'react-i18next';
import i18n from '../i18n';
import { useState } from 'react';
import PolishFlag from '../utils/pl-flag.webp';
import EnglishFlag from '../utils/en-us-flag.jpg';
import HomeIcon from '@mui/icons-material/Home';

export default function MenuAppBar() {
  const navigate = useNavigate();
  const apiClient = useApi();
  const userRole = apiClient.getUserRole();
  const { t } = useTranslation();
  const [language, setLanguage] = useState(i18n.language);

  const handleLanguageChange = () => {
    const newLanguage = language === 'en' ? 'pl' : 'en';
    i18n.changeLanguage(newLanguage);
    setLanguage(newLanguage);
  };

  return (
    <AppBar position="static" color="primary" sx={{ bgcolor: 'darkblue' }}>
      <Toolbar>
        <Link to="/home" style={{ textDecoration: 'none', color: 'inherit' }}>
          <HomeIcon />
        </Link>
        <Typography
          variant="button"
          component="div"
          sx={{ flexGrow: 1, ml: 5, textTransform: 'uppercase' }}
        >
          {t('Library Application')}
        </Typography>
        <Button
          color="inherit"
          onClick={() => {
            if (userRole === 'ROLE_ADMIN') {
              navigate('/librarian');
            } else {
              alert('You must be an ADMIN to access the Librarian Panel');
            }
          }}
        >
          {t('Librarian Panel')}
        </Button>
        <Tooltip title={t('Change language')}>
          <IconButton
            onClick={handleLanguageChange}
            sx={{ ml: 5, padding: 0, width: '30px', height: '20px' }}
          >
            <img
              src={language === 'en' ? PolishFlag : EnglishFlag}
              alt="flag"
              style={{ width: '100%', height: '100%' }}
            />
          </IconButton>
        </Tooltip>
        <Box>
          <IconButton
            size="large"
            color="inherit"
            aria-label="account"
            aria-controls="menu-appbar"
            aria-haspopup="true"
            onClick={() => navigate('/account')}
            sx={{ mr: 2, ml: 5 }}
          >
            <AccountCircle />
          </IconButton>
        </Box>
      </Toolbar>
    </AppBar>
  );
}
