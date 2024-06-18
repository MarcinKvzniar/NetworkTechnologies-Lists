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
import { useTranslation } from 'react-i18next';
import i18n from '../i18n';

function AccountDetails() {
  const [userData, setUserData] = useState<UserDto>();
  const apiClient = useApi();
  const { t } = useTranslation();

  useEffect(() => {
    apiClient.getCurrentUser().then((response) => {
      if (response.success && response.data) {
        setUserData(response.data);
      } else {
        setUserData(undefined);
      }
    });
  }, [apiClient]);

  if (!userData) {
    return <Typography justifyContent={'center'}>{t('Loading...')}</Typography>;
  }

  const handleSignOut = async () => {
    apiClient.signOut();
    window.location.href = '/login';
  };

  const locale = i18n.language;
  const formattedDateOfBirth = new Date(
    userData.dateOfBirth,
  ).toLocaleDateString(locale === 'en' ? 'en-US' : 'pl-PL');

  return (
    <Container maxWidth="sm">
      <Paper elevation={3} style={{ padding: '20px', marginTop: '20px' }}>
        <Typography variant="h4" component="h1" gutterBottom align="center">
          {t('Account Details')}
        </Typography>
        <List>
          <ListItem>
            <ListItemText primary={t('ID number')} secondary={userData.id} />
          </ListItem>
          <ListItem>
            <ListItemText primary={t('Name')} secondary={userData.name} />
          </ListItem>
          <ListItem>
            <ListItemText
              primary={t('Last Name')}
              secondary={userData.lastName}
            />
          </ListItem>
          <ListItem>
            <ListItemText
              primary={t('Date Of Birth')}
              secondary={formattedDateOfBirth}
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
          {t('Edit Details')}
        </Button>
        <Button
          variant="contained"
          color="secondary"
          startIcon={<ExitToAppIcon />}
          style={{ flexGrow: 1, marginRight: '10px' }}
          onClick={handleSignOut}
        >
          {t('Sign Out')}
        </Button>
      </Box>
    </Container>
  );
}

export default AccountDetails;
