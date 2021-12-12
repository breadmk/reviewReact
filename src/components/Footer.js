//하나의 컴포넌트를 생성 (재사용)

import styled from 'styled-components';

//styled-components  => js파일과 css파일이 하나로 되어서 관리가 편함.
const FooterList = styled.div`
   border: 1px solid black;
   height: 300px;
`;

const Footer = () => {
   return (
      <FooterList>
         <ul>
            <li>오시는길 : 서울시 구로구...</li>
            <li>전화번호 : 02-1234-1234</li>
         </ul>
      </FooterList>
   );
};

export default Footer;
