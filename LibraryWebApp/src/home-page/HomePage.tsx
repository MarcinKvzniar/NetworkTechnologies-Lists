import { Box, Button } from '@mui/material';
import { MenuAppBar } from '../menu-app-bar/MenuAppBar';
import { Outlet } from 'react-router-dom';
import { Link } from '@mui/icons-material';

export default function HomePage() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <MenuAppBar />
      <Box sx={{ display: 'flex', justifyContent: 'center' }}>
        <Button
          variant="contained"
          component={Link}
          to="settings"
          sx={{ m: 1 }}
        />
      </Box>
      <Outlet />
    </Box>
  );
}
