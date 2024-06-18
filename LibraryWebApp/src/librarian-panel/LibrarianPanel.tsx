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
            style={{ fontFamily: 'Times New Roman, serif' }}
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
                <Typography
                  variant="h6"
                  marginLeft={1}
                  style={{
                    fontFamily: 'Times New Roman, serif',
                    fontWeight: 'bold',
                  }}
                >
                  {t('User Operations')}
                </Typography>
              </Box>
              <Button
                variant="contained"
                color="primary"
                sx={{
                  marginBottom: 5,
                  backgroundColor: 'darkBlue',
                  '&:hover': { backgroundColor: 'darkBlue' },
                }}
                onClick={() => navigate('/librarian/register')}
              >
                {t('Register New User')}
              </Button>
              <Button
                variant="contained"
                color="primary"
                sx={{
                  backgroundColor: 'darkBlue',
                  '&:hover': { backgroundColor: 'darkBlue' },
                }}
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
                <Typography
                  variant="h6"
                  marginLeft={1}
                  style={{
                    fontFamily: 'Times New Roman, serif',
                    fontWeight: 'bold',
                  }}
                >
                  {t('Loan Operations')}
                </Typography>
              </Box>
              <Button
                variant="contained"
                color="primary"
                sx={{
                  marginBottom: 5,
                  backgroundColor: 'darkBlue',
                  '&:hover': { backgroundColor: 'darkBlue' },
                }}
                onClick={() => navigate('/librarian/add-loan')}
              >
                {t('Add New Loan')}
              </Button>
              <Button
                variant="contained"
                color="primary"
                sx={{
                  backgroundColor: 'darkBlue',
                  '&:hover': { backgroundColor: 'darkBlue' },
                }}
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
                <Typography
                  variant="h6"
                  marginLeft={1}
                  style={{
                    fontFamily: 'Times New Roman, serif',
                    fontWeight: 'bold',
                  }}
                >
                  {t('Book Operations')}
                </Typography>
              </Box>
              <Button
                variant="contained"
                color="primary"
                sx={{
                  marginBottom: 5,
                  backgroundColor: 'darkBlue',
                  '&:hover': { backgroundColor: 'darkBlue' },
                }}
                onClick={() => navigate('/librarian/add-book')}
              >
                {t('Add New Book')}
              </Button>
              <Button
                variant="contained"
                color="primary"
                sx={{
                  backgroundColor: 'darkBlue',
                  '&:hover': { backgroundColor: 'darkBlue' },
                }}
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
