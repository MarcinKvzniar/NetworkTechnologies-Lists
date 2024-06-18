import { Box, Button, Typography } from '@mui/material';
import { Link, Outlet, useLocation } from 'react-router-dom';
import MenuAppBar from '../menu-app-bar/MenuAppBar';
import { useEffect, useState } from 'react';
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import { Carousel } from 'react-responsive-carousel';
import { createGlobalStyle } from 'styled-components';
import { useTranslation } from 'react-i18next';
import { useApi } from '../api/ApiProvider';
import './HomePage.css';

const GlobalStyle = createGlobalStyle`
  body {
    overflow-x: hidden;
  }
`;

function HomePage() {
  const [bestsellers, setBestsellers] = useState([]);
  const location = useLocation();
  const { t } = useTranslation();
  const apiClient = useApi();

  useEffect(() => {
    apiClient.getNYTBestsellers().then((response) => {
      if (response.success && response.data) {
        setBestsellers(response.data);
      } else {
        setBestsellers([]);
      }
    });
  }, [apiClient]);

  return (
    <>
      <GlobalStyle />
      <Box sx={{ flexGrow: 1 }}>
        <MenuAppBar />
        <Box sx={{ display: 'flex', justifyContent: 'center' }}>
          <Button
            variant="contained"
            component={Link}
            to="books"
            sx={{ m: 1, bgcolor: '#3b3b3b' }}
            color="primary"
          >
            {t('Books')}
          </Button>
          <Button
            variant="contained"
            component={Link}
            to="loans"
            sx={{ m: 1, bgcolor: '#3b3b3b' }}
            color="primary"
          >
            {t('Loans')}
          </Button>
          <Button
            variant="contained"
            component={Link}
            to="catalog"
            sx={{ m: 1, bgcolor: '#3b3b3b' }}
            color="primary"
          >
            {t('Catalog')}
          </Button>
        </Box>
        <Outlet />

        {location.pathname === '/home' && (
          <Box
            sx={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              mt: 3,
              mb: 3,
            }}
          >
            <Typography
              variant="h4"
              sx={{ color: 'black', mb: 2, fontFamily: 'fantasy' }}
              style={{ fontFamily: 'fantasy' }}
            >
              {t('New York Times Bestsellers')}
            </Typography>
            <Carousel
              autoPlay
              interval={5000}
              infiniteLoop
              useKeyboardArrows
              showThumbs={false}
              showStatus={false}
            >
              {bestsellers.map((book: any) => (
                <div key={book.title}>
                  <img
                    src={book.book_image}
                    alt={book.title}
                    style={{ width: '200px', height: 'auto' }}
                  />
                  <h3>{book.title}</h3>
                  <p>{book.author}</p>
                </div>
              ))}
            </Carousel>
          </Box>
        )}
      </Box>
    </>
  );
}

export default HomePage;
