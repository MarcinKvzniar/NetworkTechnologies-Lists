import { Button, Box, Typography, Grid } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import PersonIcon from '@mui/icons-material/Person';
import BookIcon from '@mui/icons-material/Book';
import ChangeCircleIcon from '@mui/icons-material/ChangeCircle';
import MenuAppBar from '../menu-app-bar/MenuAppBar';
import { useTranslation } from 'react-i18next';

export default function LibrarianPanel() {
  const navigate = useNavigate();
  const { t } = useTranslation();

  return (
    <Box sx={{ flexGrow: 1 }}>
      <MenuAppBar />
      <Box
        display="flex"
        flexDirection="column"
        alignItems="center"
        justifyContent="center"
        marginTop={10}
      >
        <Box marginBottom={10}>
          <Typography
            variant="h4"
            component="h1"
            gutterBottom
            fontWeight={'bold'}
          >
            {t('Librarian Panel')}
          </Typography>
        </Box>

        <Grid container spacing={3}>
          <Grid item xs={4}>
            <Box
              marginBottom={3}
              display="flex"
              flexDirection="column"
              alignItems="center"
            >
              <Box
                display="flex"
                flexDirection="row"
                alignItems="center"
                marginBottom={5}
              >
                <PersonIcon fontSize="large" />
                <Typography variant="h6" marginLeft={1}>
                  {t('User Operations')}
                </Typography>
              </Box>
              <Button
                variant="contained"
                color="primary"
                onClick={() => navigate('/librarian/register')}
                sx={{ marginBottom: 5 }}
              >
                {t('Register New User')}
              </Button>
              <Button
                variant="contained"
                color="primary"
                onClick={() => navigate('/librarian/users')}
              >
                {t('List Of Users')}
              </Button>
            </Box>
          </Grid>

          <Grid item xs={4}>
            <Box
              marginBottom={3}
              display="flex"
              flexDirection="column"
              alignItems="center"
            >
              <Box
                display="flex"
                flexDirection="row"
                alignItems="center"
                marginBottom={5}
              >
                <ChangeCircleIcon fontSize="large" />
                <Typography variant="h6" marginLeft={1}>
                  {t('Loan Operations')}
                </Typography>
              </Box>
              <Button
                variant="contained"
                color="primary"
                onClick={() => navigate('/librarian/add-loan')}
                sx={{ marginBottom: 5 }}
              >
                {t('Add New Loan')}
              </Button>
              <Button
                variant="contained"
                color="primary"
                onClick={() => navigate('/librarian/loans')}
              >
                {t('List Of Loans')}
              </Button>
            </Box>
          </Grid>

          <Grid item xs={4}>
            <Box
              marginBottom={3}
              display="flex"
              flexDirection="column"
              alignItems="center"
            >
              <Box
                display="flex"
                flexDirection="row"
                alignItems="center"
                marginBottom={5}
              >
                <BookIcon fontSize="large" />
                <Typography variant="h6" marginLeft={1}>
                  {t('Book Operations')}
                </Typography>
              </Box>
              <Button
                variant="contained"
                color="primary"
                onClick={() => navigate('/librarian/add-book')}
                sx={{ marginBottom: 5 }}
              >
                {t('Add New Book')}
              </Button>
              <Button
                variant="contained"
                color="primary"
                onClick={() => navigate('/librarian/delete-book')}
              >
                {t('Delete Book')}
              </Button>
            </Box>
          </Grid>
        </Grid>
      </Box>
    </Box>
  );
}
