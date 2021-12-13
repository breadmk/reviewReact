import React from 'react';
import { useState } from 'react/cjs/react.development';
import Footer from '../components/Footer';
import Header from '../components/Header';
import Home from '../components/home/Home';

const HomePage = () => {
   // http 요청(jquery ajax(사용x),fetch,axios(다운))
   const [boards, setBoards] = useState([]);
   return (
      <div>
         <Header />
         <Home />
         <Footer />
      </div>
   );
};

export default HomePage;
