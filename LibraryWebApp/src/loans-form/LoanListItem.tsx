import {
  Button,
  ListItem,
  ListItemSecondaryAction,
  ListItemText,
} from '@mui/material';
import { UserDto } from '../api/dto/user/user.dto';
import { BookResponseDto } from '../api/dto/book/book-response.dto';
import React, { useState } from 'react';
import { useApi } from '../api/ApiProvider';

interface LoanListItemProps {
  loan: {
    id: number;
    loanDate: string;
    dueDate: string;
    returnDate: string;
    book: BookResponseDto;
    user: UserDto[];
  };
}

function LoanListItem({ loan }: LoanListItemProps) {
  const loanDate = new Date(loan.loanDate);
  const formattedLoanDate = `${loanDate.getFullYear()}-${String(loanDate.getMonth() + 1).padStart(2, '0')}-${String(loanDate.getDate()).padStart(2, '0')}`;

  const dueDate = new Date(loan.dueDate);
  const formattedDueDate = `${dueDate.getFullYear()}-${String(dueDate.getMonth() + 1).padStart(2, '0')}-${String(dueDate.getDate()).padStart(2, '0')}`;

  const [returnDate, setReturnDate] = useState(loan.returnDate);
  let returnDateObj = null;
  let isReturned = false;
  let formattedReturnDate = 'Not returned';

  if (Array.isArray(returnDate) && returnDate.length === 3) {
    returnDateObj = new Date(returnDate[0], returnDate[1] - 1, returnDate[2]);
    isReturned = returnDateObj > loanDate;
    formattedReturnDate = `${returnDateObj.getFullYear()}-${String(returnDateObj.getMonth() + 1).padStart(2, '0')}-${String(returnDateObj.getDate()).padStart(2, '0')}`;
  }

  const apiClient = useApi();

  const handleReturn = async () => {
    const confirmReturn = window.confirm(
      'Are you sure you want to return this book?',
    );
    if (confirmReturn) {
      const today = new Date();
      const returnDate = [
        today.getFullYear(),
        today.getMonth() + 1,
        today.getDate(),
      ];
      const returnDateString = `${returnDate[0]}-${returnDate[1]}-${returnDate[2]}`;
      const response = await apiClient.returnLoan(loan.id);
      if (response.success && response.data) {
        setReturnDate(returnDateString);
        alert('Loan returned successfully');
      } else {
        alert('Failed to return loan');
      }
    }
  };

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
            <div>Return Date: {formattedReturnDate}</div>
            {isReturned && <div>The book has been returned.</div>}
          </>
        }
      />
      <ListItemSecondaryAction>
        <Button
          variant="contained"
          color="primary"
          sx={{ m: 1, bgcolor: '#3b3b3b' }}
          disabled={formattedReturnDate !== 'Not returned'}
          onClick={handleReturn}
        >
          RETURN
        </Button>
      </ListItemSecondaryAction>
    </ListItem>
  );
}

export default LoanListItem;
