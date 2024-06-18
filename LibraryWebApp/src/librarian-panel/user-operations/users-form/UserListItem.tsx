import { ListItem, ListItemText } from '@mui/material';
import { useTranslation } from 'react-i18next';
import i18n from '../../../i18n';

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
  const { t } = useTranslation();
  const locale = i18n.language;
  let formattedDateOfBirth: string = new Date(
    user.dateOfBirth,
  ).toLocaleDateString(locale === 'en' ? 'en-US' : 'pl-PL');

  if (
    formattedDateOfBirth === '1.01.1970' ||
    formattedDateOfBirth === '1/1/1970'
  ) {
    formattedDateOfBirth = '';
  }

  return (
    <ListItem>
      <ListItemText
        primary={`${user.id} - ${user.name} ${user.lastName}`}
        secondary={
          <>
            <div>
              {' '}
              <b> {t('Date Of Birth')}: </b> {formattedDateOfBirth}{' '}
            </div>
            <div>
              {' '}
              <b>Email: </b> {user.email}{' '}
            </div>
          </>
        }
      />
    </ListItem>
  );
}

export default UserListItem;
