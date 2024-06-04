import { ListItem, ListItemText } from '@mui/material';

interface LoanListItemProps {
  loan: {
    id: number;
    loanDate: string;
    dueDate: string;
    user: {
      id: number;
      name: string;
      lastName: string;
      dateOfBirth: string;
      email: string;
    };
    book: {
      id: number;
      isbn: string;
      title: string;
      author: string;
      publisher: string;
      yearPublished: number;
      isAvailable: boolean;
    };
  };
}

function LoanListItem({ loan }: LoanListItemProps) {
  return (
    <ListItem>
      <ListItemText
        primary={`${loan.book.title}`}
        secondary={
          <>
            <div>ISBN: {loan.book.isbn}</div>
            <div>Author: {loan.book.author}</div>
            <div>Loan Date: {loan.loanDate}</div>
            <div>Due Date: {loan.dueDate}</div>
          </>
        }
      />
    </ListItem>
  );
}

export default LoanListItem;
