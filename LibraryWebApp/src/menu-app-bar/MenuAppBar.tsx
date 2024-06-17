import {
  AppBar,
  Box,
  Button,
  IconButton,
  Toolbar,
  Typography,
} from '@mui/material';
import { AccountCircle } from '@mui/icons-material';
import { useNavigate, Link } from 'react-router-dom';
import { useApi } from '../api/ApiProvider';

export default function MenuAppBar() {
  const navigate = useNavigate();
  const apiClient = useApi();
  const userRole = apiClient.getUserRole();

  return (
    <AppBar position="static" color="primary" sx={{ bgcolor: 'darkblue' }}>
      <Toolbar>
        <IconButton
          size="large"
          edge="start"
          color="inherit"
          aria-label="menu"
          sx={{ mr: 2 }}
        ></IconButton>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          <Link to="/home" style={{ textDecoration: 'none', color: 'inherit' }}>
            Library Application
          </Link>
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
          Librarian Panel
        </Button>
        <Box>
          <IconButton
            size="large"
            color="inherit"
            aria-label="account"
            aria-controls="menu-appbar"
            aria-haspopup="true"
            onClick={() => navigate('/account')}
            sx={{ mr: 2 }}
          >
            <AccountCircle />
          </IconButton>
        </Box>
      </Toolbar>
    </AppBar>
  );
}
