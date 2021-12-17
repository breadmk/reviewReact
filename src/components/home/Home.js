import React from 'react';
import styled from 'styled-components';

const StyledDeleteButton = styled.button`
   color: ${(props) => (props.user.username === 'ssar' ? 'blue' : 'red')};
`;

//부모로 부터 받아온 어떤 데이터를 가지고 스타일링을 동적으로 할거라면?
const Home = (props) => {
   //구조분할 할당
   //const { boards, id } = props; 이렇게 하면 다 각각 받아줌
   const { boards, setBoards, number, setNumber, user } = props;

   console.log(user.username);

   return (
      <div>
         <h1>홈:{number} </h1>
         <button
            onClick={() => {
               setNumber(number + 1);
            }}
         >
            번호증가
         </button>
         <StyledDeleteButton
            user={user}
            onClick={() => {
               setBoards([]);
            }}
         >
            전체삭제
         </StyledDeleteButton>
         {boards.map((board) => (
            <h3>
               제목: {board.title} / 내용: {board.content}
            </h3>
         ))}
      </div>
   );
};

export default Home;
