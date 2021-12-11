import './App.css';
import { useState } from 'react';

function App() {
   console.log('App실행됨');

   const [num, setNum] = useState(5);

   let sample = [
      { id: 1, name: '홍길동' },
      { id: 2, name: '임꺽정' },
      { id: 3, name: '장보고' },
      { id: 4, name: '코스' },
   ];
   //다운로드 받음
   const [users, setUsers] = useState(sample); //래퍼런스 변경되야 동작

   const download = () => {
      setUsers([...sample, { id: num, name: '러브' }]);
      setNum(num + 1);
   };

   //랜더링 시점 = 상태값 변경
   return (
      <div>
         <button onClick={download}>다운로드</button>
         {users.map((u) => (
            <h1>
               {u.id}.{u.name}
            </h1>
         ))}
      </div>
   );
}

export default App;
