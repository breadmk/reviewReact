import Button from '@restart/ui/esm/Button';
import React from 'react';
import { Container, Form, FormControl, Nav, Navbar } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

//하나의 컴포넌트를 생성 (재사용)

//styled-components  => js파일과 css파일이 하나로 되어서 관리가 편함.
const StyledHeaderDiv = styled.div`
   border: 1px solid black;
   height: 300px;
   background-color: ${(props) => props.backgroundColor};
`;

//Link 객체 디자인 하는법
const StyledHeadLink = styled(Link)`
   color: red;
`;

const Header = () => {
   return (
      <>
         <StyledHeaderDiv backgroundColor="blue">
            <ul>
               <li>
                  <StyledHeadLink to="/">홈</StyledHeadLink>
               </li>
               <li>
                  <StyledHeadLink to="/login">로그인</StyledHeadLink>
               </li>
            </ul>
         </StyledHeaderDiv>
         <>
            <Navbar bg="dark" variant="dark">
               <Container>
                  <Navbar.Brand href="#home">Navbar</Navbar.Brand>
                  <Nav className="me-auto">
                     <Link to="/" className="nav-link">
                        Home
                     </Link>
                     <Link to="/login" className="nav-link">
                        Login
                     </Link>
                  </Nav>
                  <Form className="d-flex">
                     <FormControl
                        type="search"
                        placeholder="Search"
                        className="me-2"
                        aria-label="Search"
                     />
                     <Button variant="outline-success">Search</Button>
                  </Form>
               </Container>
            </Navbar>
            <br />
         </>
      </>
   );
};

export default Header;
