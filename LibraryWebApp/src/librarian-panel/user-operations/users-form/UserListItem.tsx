import { ListItem, ListItemText } from '@mui/material';

interface UserListItemProps {
  user: {
    id: number;
    name: string;
    lastName: string;
    dateOfBirth: string;
    email: string;
  };
}

function UserListItem({ user }: UserListItemProps) {
  return (
    <ListItem>
      <ListItemText
        primary={`${user.id} - ${user.name} ${user.lastName}`}
        secondary={
          <>
            <div>
              {' '}
              <b>Date Of Birth: </b> {user.dateOfBirth}
            </div>
            <div>
              {' '}
              <b>Email: </b> {user.email}
            </div>
          </>
        }
      />
    </ListItem>
  );
}

export default UserListItem;
