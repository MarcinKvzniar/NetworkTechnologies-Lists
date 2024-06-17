import { useEffect, useState } from 'react';
import { useApi } from '../api/ApiProvider';
import { UserDto } from '../api/dto/user/user.dto';
import {
  Box,
  Button,
  Container,
  List,
  ListItem,
  ListItemText,
  Paper,
  Typography,
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';

function AccountDetails() {
  const [userData, setUserData] = useState<UserDto>();
  const apiClient = useApi();

  useEffect(() => {
    apiClient.getCurrentUser().then((response) => {
      if (response.success && response.data) {
        setUserData(response.data);
        console.log(`response data: ${response.data}`);
      } else {
        setUserData(undefined);
      }
    });
  }, [apiClient]);

  if (!userData) {
    return <div>Loading...</div>;
  }

  const handleSignOut = async () => {
    apiClient.signOut();
    window.location.href = '/login';
  };

  return (
    <Container maxWidth="sm">
      <Paper elevation={3} style={{ padding: '20px', marginTop: '20px' }}>
        <Typography variant="h4" component="h1" gutterBottom align="center">
          Account Details
        </Typography>
        <List>
          <ListItem>
            <ListItemText primary="User ID" secondary={userData.id} />
          </ListItem>
          <ListItem>
            <ListItemText primary="Name" secondary={userData.name} />
          </ListItem>
          <ListItem>
            <ListItemText primary="Last Name" secondary={userData.lastName} />
          </ListItem>
          <ListItem>
            <ListItemText
              primary="Date of Birth"
              secondary={userData.dateOfBirth}
            />
          </ListItem>
          <ListItem>
            <ListItemText primary="Email" secondary={userData.email} />
          </ListItem>
        </List>
      </Paper>
      <Box display="flex" justifyContent="space-between" marginTop={2}>
        <Button
          variant="contained"
          color="primary"
          startIcon={<EditIcon />}
          style={{ flexGrow: 1, marginRight: '10px' }}
        >
          Edit Details
        </Button>
        <Button
          variant="contained"
          color="secondary"
          startIcon={<ExitToAppIcon />}
          style={{ flexGrow: 1, marginRight: '10px' }}
          onClick={handleSignOut}
        >
          Sign Out
        </Button>
      </Box>
    </Container>
  );
}

export default AccountDetails;
