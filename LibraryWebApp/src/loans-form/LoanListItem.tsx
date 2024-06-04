import { ListItem, ListItemText } from '@mui/material';
import { UserDto } from '../api/dto/user/user.dto';
import { BookResponseDto } from '../api/dto/book/book-response.dto';

interface LoanListItemProps {
  loan: {
    id: number;
    loanDate: string;
    dueDate: string;
    user: UserDto[];
    book: BookResponseDto;
  };
}

function LoanListItem({ loan }: LoanListItemProps) {
  const loanDate = new Date(loan.loanDate);
  const formattedLoanDate = `${loanDate.getFullYear()}-${String(loanDate.getMonth() + 1).padStart(2, '0')}-${String(loanDate.getDate()).padStart(2, '0')}`;

  const dueDate = new Date(loan.dueDate);
  const formattedDueDate = `${dueDate.getFullYear()}-${String(dueDate.getMonth() + 1).padStart(2, '0')}-${String(dueDate.getDate()).padStart(2, '0')}`;

  return (
    <ListItem>
      <ListItemText
        primary={`${loan.book.title}`}
        secondary={
          <>
            <div>ISBN: {loan.book.isbn}</div>
            <div>Author: {loan.book.author}</div>
            <div>Loan Date: {formattedLoanDate}</div>
            <div>Due Date: {formattedDueDate}</div>
          </>
        }
      />
    </ListItem>
  );
}

export default LoanListItem;
