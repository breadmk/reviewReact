import React from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import Login from '../components/login/Login';

const LoginPage = (props) => {
   const { history } = props;
   console.log('LoginPage', props.match.params.id);
   // props.history.goBack();
   return (
      <div>
         <button
            onClick={() => {
               history.goBack(); // 뒤로가기
               // props.history.go(-1); 앞으로 가기 뒤로가기
               // props.history.push('/'); 경로로 가기
            }}
         >
            뒤로가기
         </button>
         <Login />
      </div>
   );
};

export default LoginPage;
