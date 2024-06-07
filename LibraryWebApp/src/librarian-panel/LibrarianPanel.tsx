import { Button, Box, Typography, Grid } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import PersonIcon from '@mui/icons-material/Person';
import BookIcon from '@mui/icons-material/Book';
import ChangeCircleIcon from '@mui/icons-material/ChangeCircle';

export default function LibrarianPanel() {
  const navigate = useNavigate();

  return (
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
          Welcome to Librarian Panel
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
                User Operations
              </Typography>
            </Box>
            <Button
              variant="contained"
              color="primary"
              onClick={() => navigate('/librarian/register')}
              sx={{ marginBottom: 5 }}
            >
              Register New User
            </Button>
            <Button
              variant="contained"
              color="primary"
              onClick={() => navigate('/librarian/users')}
            >
              List Of Users
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
                Loan Operations
              </Typography>
            </Box>
            <Button
              variant="contained"
              color="primary"
              onClick={() => navigate('/librarian/add-loan')}
            >
              Add New Loan
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
                Book Operations
              </Typography>
            </Box>
            <Button
              variant="contained"
              color="primary"
              onClick={() => navigate('/librarian/add-book')}
            >
              Add New Book
            </Button>
            <Button
              variant="contained"
              color="primary"
              onClick={() => navigate('/librarian/delete-book')}
            >
              Delete Book
            </Button>
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
}
