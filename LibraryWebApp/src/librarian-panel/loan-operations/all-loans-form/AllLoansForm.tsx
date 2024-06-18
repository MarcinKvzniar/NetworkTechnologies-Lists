import { useEffect, useState } from 'react';
import { BookResponseDto } from '../../../api/dto/book/book-response.dto';
import { UserDto } from '../../../api/dto/user/user.dto';
import { useApi } from '../../../api/ApiProvider';
import { Box, Button, Container, Grid, List, Typography } from '@mui/material';
import LoanItem from './LoanItem';
import { useTranslation } from 'react-i18next';

interface Loan {
  id: number;
  loanDate: string;
  dueDate: string;
  returnDate: string;
  user: UserDto[];
  book: BookResponseDto;
}

interface LoansProps {
  loans: Loan[];
}

function AllLoans({ loans }: LoansProps) {
  const [loanData, setLoanData] = useState<Loan[]>([]);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const apiClient = useApi();
  const { t } = useTranslation();

  useEffect(() => {
    apiClient.getAllLoans(page).then((response) => {
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
          <Typography
            component="h1"
            variant="h2"
            align="center"
            color="Black"
            gutterBottom
            className="header"
          >
            {t('All Loans')}
          </Typography>
          <Box display="flex" justifyContent="center" alignItems="center">
            <List className="LoanList">
              {loanData.map((loan) => (
                <div key={loan.id} className="LoanListItem">
                  <LoanItem key={loan.id} loan={loan} />
                </div>
              ))}
            </List>
            <Box display="flex" justifyContent="center" mt={2} mb={1}>
              <Button
                variant="contained"
                color="primary"
                onClick={handlePrevious}
                style={{ marginRight: '10px', marginLeft: '10px' }}
                disabled={page === 0}
              >
                {t('Previous')}
              </Button>
              <Button
                variant="contained"
                color="primary"
                onClick={handleNext}
                disabled={!hasMore}
              >
                {t('Next')}
              </Button>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </Container>
  );
}

export default AllLoans;
