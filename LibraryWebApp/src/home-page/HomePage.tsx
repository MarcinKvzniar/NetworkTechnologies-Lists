import { Box, Button } from '@mui/material';
import { Link, Outlet } from 'react-router-dom';
import MenuAppBar from '../menu-app-bar/MenuAppBar';

function HomePage() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <MenuAppBar />
      <Box sx={{ display: 'flex', justifyContent: 'center' }}>
        <Button variant="contained" component={Link} to="books" sx={{ m: 1 }}>
          Books
        </Button>
        <Button variant="contained" component={Link} to="loans" sx={{ m: 1 }}>
          Loans
        </Button>
        <Button variant="contained" component={Link} to="catalog" sx={{ m: 1 }}>
          Catalog
        </Button>
      </Box>
      <Outlet />
    </Box>
  );
}

export default HomePage;
