import React, { useEffect } from 'react';
import { useState } from 'react/cjs/react.development';
import Footer from '../components/Footer';
import Header from '../components/Header';
import Home from '../components/home/Home';

const HomePage = () => {
   // http 요청(jquery ajax(사용x),fetch,axios(다운))
   const [boards, setBoards] = useState([]);
   const [number, setNumber] = useState(0);
   const [user, setUser] = useState({});

   // 빈 배열로 해두면 무조건 한번만 실행.
   useEffect(() => {
      //다운로드 가정 = fetch(),axios(),ajax() || 원래는 block 처리되는데 비동기로 pass
      let data = [
         { id: 1, title: '제목1', content: '내용1' },
         { id: 2, title: '제목2', content: '내용2' },
         { id: 3, title: '제목3', content: '내용3' },
      ];
      setBoards([...data]);
      setUser({ id: 1, username: 'cos' });
   }, []);

   return (
      <div>
         <Header />
         <Home
            boards={boards}
            setBoards={setBoards}
            number={number}
            setNumber={setNumber}
            user={user}
         />
         <Footer />
      </div>
   );
};

export default HomePage;
