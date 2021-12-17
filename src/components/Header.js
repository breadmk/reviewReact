import React from 'react';
import styled from 'styled-components';

//하나의 컴포넌트를 생성 (재사용)

//styled-components  => js파일과 css파일이 하나로 되어서 관리가 편함.
const StyledHeaderDiv = styled.div`
   border: 1px solid black;
   height: 300px;
   background-color: ${(props) => props.backgroundColor};
`;

const Header = () => {
   return (
      <StyledHeaderDiv backgroundColor="blue">
         <ul>
            <li>메뉴1</li>
            <li>메뉴2</li>
         </ul>
      </StyledHeaderDiv>
   );
};

export default Header;
