import { useEffect, useState } from 'react';
import { useApi } from '../../../api/ApiProvider';
import { Box, Container, Grid, List, Typography } from '@mui/material';
import UserListItem from './UserListItem';

interface User {
  id: number;
  name: string;
  lastName: string;
  dateOfBirth: string;
  email: string;
}

interface UserListProps {
  users: User[];
}

function UserList({ users }: UserListProps) {
  const [userData, setUserData] = useState<User[]>([]);
  const apiClient = useApi();

  useEffect(() => {
    apiClient.getUsers().then((response) => {
      if (response.success && response.data) {
        console.log(response);
        setUserData(response.data);
      } else {
        setUserData([]);
        console.log(response);
      }
    });
  }, [apiClient]);

  return (
    <Container maxWidth="lg">
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <Typography
            component="h1"
            variant="h2"
            align="center"
            color="Black"
            gutterBottom
            className="header"
          >
            Registered Users
          </Typography>
          <Box display="flex" justifyContent="center">
            <List className="UserList">
              {userData.map((user) => (
                <div key={user.id} className="UserListItem">
                  <UserListItem key={user.id} user={user} />
                </div>
              ))}
            </List>
          </Box>
        </Grid>
      </Grid>
    </Container>
  );
}

export default UserList;
