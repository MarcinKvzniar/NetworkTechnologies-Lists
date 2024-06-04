import { Button, Box, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';

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

      <Box marginBottom={3}>
        <Button
          variant="contained"
          color="primary"
          onClick={() => navigate('/librarian/register')}
        >
          Register New User
        </Button>
      </Box>

      <Box marginBottom={3}>
        <Button
          variant="contained"
          color="primary"
          onClick={() => navigate('/librarian/add-loan')}
        >
          Add New Loan
        </Button>
      </Box>

      <Box marginBottom={3}>
        <Button
          variant="contained"
          color="primary"
          onClick={() => navigate('librarian/add-book')}
        >
          Add New Book
        </Button>
      </Box>
    </Box>
  );
}
