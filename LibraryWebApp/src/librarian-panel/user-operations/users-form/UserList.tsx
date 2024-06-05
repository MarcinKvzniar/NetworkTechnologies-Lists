import { useEffect, useState } from 'react';
import { useApi } from '../../../api/ApiProvider';
import { Box, Button, Container, Grid, List, Typography } from '@mui/material';
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
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const apiClient = useApi();

  useEffect(() => {
    apiClient.getUsers(page).then((response) => {
      if (response.success && response.data) {
        setUserData(response.data.users);
        setHasMore(response.data.hasMore);
      } else {
        setUserData([]);
      }
    });
  }, [apiClient, page]);

  const handleNext = () => {
    if (hasMore) {
      setPage((prevPage) => prevPage + 1);
    }
  };

  const handlePrevious = () => {
    setPage((prevPage) => Math.max(prevPage - 1, 0));
  };

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
          <Box display="flex" justifyContent="center" alignItems="center">
            <List className="UserList">
              {userData.map((user) => (
                <div key={user.id} className="UserListItem">
                  <UserListItem key={user.id} user={user} />
                </div>
              ))}
            </List>
            <Box display="flex" justifyContent="center" mt={2} mb={1}>
              <Button
                variant="contained"
                color="primary"
                onClick={handlePrevious}
                style={{ marginRight: '10px' }}
                disabled={page === 0}
              >
                Previous
              </Button>
              <Button
                variant="contained"
                color="primary"
                onClick={handleNext}
                disabled={!hasMore}
              >
                Next
              </Button>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </Container>
  );
}

export default UserList;
