import { useState } from 'react';
import { BookResponseDto } from '../../../api/dto/book/book-response.dto';
import { UserDto } from '../../../api/dto/user/user.dto';
import { ListItem, ListItemText } from '@mui/material';

interface LoanItemProps {
  loan: {
    id: number;
    loanDate: string;
    dueDate: string;
    returnDate: string;
    user: UserDto[];
    book: BookResponseDto;
  };
}

function LoanItem({ loan }: LoanItemProps) {
  const loanDate = new Date(loan.loanDate);
  const formattedLoanDate = `${loanDate.getFullYear()}-${String(loanDate.getMonth() + 1).padStart(2, '0')}-${String(loanDate.getDate()).padStart(2, '0')}`;

  const dueDate = new Date(loan.dueDate);
  const formattedDueDate = `${dueDate.getFullYear()}-${String(dueDate.getMonth() + 1).padStart(2, '0')}-${String(dueDate.getDate()).padStart(2, '0')}`;

  const [returnDate] = useState(loan.returnDate);
  let returnDateObj = null;
  let isReturned = false;
  let formattedReturnDate = 'Not returned';

  if (Array.isArray(returnDate) && returnDate.length === 3) {
    returnDateObj = new Date(returnDate[0], returnDate[1] - 1, returnDate[2]);
    isReturned = returnDateObj > loanDate;
    formattedReturnDate = `${returnDateObj.getFullYear()}-${String(returnDateObj.getMonth() + 1).padStart(2, '0')}-${String(returnDateObj.getDate()).padStart(2, '0')}`;
  }

  return (
    <ListItem>
      <ListItemText
        primary={`${loan.id} - ${loan.book.title}`}
        secondary={
          <>
            <div>
              {' '}
              <b>ISBN:</b> {loan.book.isbn}
            </div>
            <div>
              {' '}
              <b>Author: </b> {loan.book.author}
            </div>
            <div>
              {' '}
              <b>Loan Date: </b> {formattedLoanDate}
            </div>
            <div>
              {' '}
              <b>Due Date: </b> {formattedDueDate}
            </div>
            <div>
              {' '}
              <b>Return Date: </b> {formattedReturnDate}{' '}
            </div>
            {isReturned && <div>The book has been returned.</div>}
          </>
        }
      />
    </ListItem>
  );
}

export default LoanItem;
