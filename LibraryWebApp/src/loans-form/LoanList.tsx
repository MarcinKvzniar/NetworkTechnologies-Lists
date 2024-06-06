import { List, Container, Grid, Box, Button } from '@mui/material';
import LoanListItem from './LoanListItem';
import './LoanList.css';
import { useEffect, useState } from 'react';
import { useApi } from '../api/ApiProvider';
import { UserDto } from '../api/dto/user/user.dto';
import { BookResponseDto } from '../api/dto/book/book-response.dto';

interface Loan {
  id: number;
  loanDate: string;
  dueDate: string;
  user: UserDto[];
  book: BookResponseDto;
}

interface LoanListProps {
  loans: Loan[];
}

function LoanList({ loans }: LoanListProps) {
  const [loanData, setLoanData] = useState<Loan[]>([]);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const apiClient = useApi();

  useEffect(() => {
    apiClient.getLoans(page).then((response) => {
      if (response.success && response.data) {
        setLoanData(response.data.loans);
        setHasMore(response.data.hasMore);
      } else {
        setLoanData([]);
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
          <List className="LoanList">
            {loanData.map((loan) => (
              <div key={loan.id} className="LoanListItem">
                <LoanListItem key={loan.id} loan={loan} />
              </div>
            ))}
          </List>
          <Box display="flex" justifyContent="center" mt={2} mb={1}>
            <Button
              variant="contained"
              color="primary"
              sx={{ backgroundColor: 'DarkBlue' }}
              onClick={handlePrevious}
              style={{ marginRight: '10px' }}
              disabled={page === 0}
            >
              Previous
            </Button>
            <Button
              variant="contained"
              color="primary"
              sx={{ backgroundColor: 'DarkBlue' }}
              onClick={handleNext}
              disabled={!hasMore}
            >
              Next
            </Button>
          </Box>
        </Grid>
      </Grid>
    </Container>
  );
}

export default LoanList;
