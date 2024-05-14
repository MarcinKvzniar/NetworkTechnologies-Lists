import { List, Container, Grid } from '@mui/material';
import LoanListItem from './LoanListItem';
import './LoanList.css';

interface Loan {
  id: number;
  loanDate: string;
  dueDate: string;
  user: {
    id: number;
    name: string;
    lastName: string;
    dob: string;
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
}

interface LoanListProps {
  loans: Loan[];
}

const mockLoans = [
  {
    id: 1,
    loanDate: '2024-05-01',
    dueDate: '2024-05-15',
    user: {
      id: 1,
      name: 'John',
      lastName: 'Doe',
      dob: '1990-01-01',
      email: 'john@example.com',
    },
    book: {
      id: 101,
      isbn: '978-0451524935',
      title: 'To Kill a Mockingbird',
      author: 'Harper Lee',
      publisher: 'Harper Perennial Modern Classics',
      yearPublished: 1960,
      isAvailable: false,
    },
  },
  {
    id: 2,
    loanDate: '2024-05-03',
    dueDate: '2024-05-17',
    user: {
      id: 2,
      name: 'Jane',
      lastName: 'Smith',
      dob: '1985-05-10',
      email: 'jane@example.com',
    },
    book: {
      id: 102,
      isbn: '978-0142437209',
      title: '1984',
      author: 'George Orwell',
      publisher: 'Penguin Books',
      yearPublished: 1949,
      isAvailable: false,
    },
  },
  {
    id: 3,
    loanDate: '2024-05-05',
    dueDate: '2024-05-19',
    user: {
      id: 3,
      name: 'Alice',
      lastName: 'Johnson',
      dob: '1988-08-15',
      email: 'alice@example.com',
    },
    book: {
      id: 103,
      isbn: '978-0061120084',
      title: 'The Catcher in the Rye',
      author: 'J.D. Salinger',
      publisher: 'Back Bay Books',
      yearPublished: 1951,
      isAvailable: false,
    },
  },
  {
    id: 4,
    loanDate: '2024-05-07',
    dueDate: '2024-05-21',
    user: {
      id: 4,
      name: 'Michael',
      lastName: 'Brown',
      dob: '1995-03-20',
      email: 'michael@example.com',
    },
    book: {
      id: 104,
      isbn: '978-0743273565',
      title: 'The Great Gatsby',
      author: 'F. Scott Fitzgerald',
      publisher: 'Scribner',
      yearPublished: 1925,
      isAvailable: false,
    },
  },
  {
    id: 5,
    loanDate: '2024-05-10',
    dueDate: '2024-05-24',
    user: {
      id: 5,
      name: 'Emily',
      lastName: 'Davis',
      dob: '1992-11-25',
      email: 'emily@example.com',
    },
    book: {
      id: 105,
      isbn: '978-1400033416',
      title: 'The Adventures of Huckleberry Finn',
      author: 'Mark Twain',
      publisher: 'Modern Library',
      yearPublished: 1884,
      isAvailable: false,
    },
  },
];

function LoanList({ loans }: LoanListProps) {
  return (
    <>
      <Container maxWidth="lg">
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <List className="LoanList">
              {mockLoans.map((loan) => (
                <div key={loan.id} className="LoanListItem">
                  <LoanListItem key={loan.id} loan={loan} />
                </div>
              ))}
            </List>
          </Grid>
        </Grid>
      </Container>
    </>
  );
}

export default LoanList;
