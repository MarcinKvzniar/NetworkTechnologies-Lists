import { useEffect, useState } from 'react';
import { useApi } from '../api/ApiProvider';
import { UserDto } from '../api/dto/user/user.dto';
import {
  Box,
  Button,
  Container,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  List,
  ListItem,
  ListItemText,
  Paper,
  TextField,
  Typography,
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import { useTranslation } from 'react-i18next';
import i18n from '../i18n';
import { PatchUserDto } from '../api/dto/user/patch-user.dto';
import MenuAppBar from '../menu-app-bar/MenuAppBar';

function AccountDetails() {
  const [, setPatchData] = useState<PatchUserDto>();
  const [userData, setUserData] = useState<UserDto>();
  const apiClient = useApi();
  const { t } = useTranslation();
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editedData, setEditedData] = useState<Partial<UserDto> | undefined>(
    userData,
  );

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

  const handleEditClick = () => {
    setIsDialogOpen(true);
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEditedData({
      ...editedData,
      [event.target.name]: event.target.value,
    });
  };

  const handleApproveClick = () => {
    const finalData = {
      name: editedData?.email || userData.name,
      lastName: editedData?.lastName || userData.lastName,
      dateOfBirth: editedData?.dateOfBirth || userData.dateOfBirth,
      email: editedData?.name || userData.email,
    };

    apiClient
      .patchUser(finalData, userData.id)
      .then(() => {
        setIsDialogOpen(false);
        setPatchData(finalData);
      })
      .catch((error) => {
        alert(t('Failed to update user details:') + error.message);
      });
  };

  const handleClose = () => {
    setIsDialogOpen(false);
  };

  const locale = i18n.language;
  const formattedDateOfBirth = new Date(
    userData.dateOfBirth,
  ).toLocaleDateString(locale === 'en' ? 'en-US' : 'pl-PL');

  return (
    <>
      <MenuAppBar />
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
            onClick={handleEditClick}
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
        <Dialog open={isDialogOpen} onClose={handleClose}>
          <DialogTitle>
            <Typography align="center">{t('Edit Details')}</Typography>
          </DialogTitle>
          <DialogContent>
            <Box
              display="flex"
              flexDirection="column"
              justifyContent="center"
              alignItems="center"
            >
              <TextField
                name="name"
                label={t('Name')}
                value={editedData?.name || ''}
                onChange={handleInputChange}
              />
              <TextField
                name="lastName"
                label={t('Last Name')}
                value={editedData?.lastName || ''}
                onChange={handleInputChange}
              />
              <TextField
                name="dateOfBirth"
                label={t('Date Of Birth') + ' (yyyy-mm-dd)'}
                value={editedData?.dateOfBirth || ''}
                onChange={handleInputChange}
              />
              <TextField
                name="email"
                label={t('Email')}
                value={editedData?.email || ''}
                onChange={handleInputChange}
              />
            </Box>
          </DialogContent>
          <DialogActions>
            <Button
              onClick={() => {
                handleApproveClick();
                window.location.reload();
              }}
            >
              {t('Approve')}
            </Button>
            <Button onClick={handleClose}>{t('Cancel')}</Button>
          </DialogActions>
        </Dialog>
      </Container>
    </>
  );
}

export default AccountDetails;
