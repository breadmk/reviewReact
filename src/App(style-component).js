import styled from 'styled-components';
import './App.css';
import { Title } from './myCss';

const a = {
   backgroundColor: 'red',
};
function App() {
   return (
      <div>
         <div style={a}>안녕</div>
         <div className="box-style">헬로</div>
         <Title>방가워</Title>
      </div>
   );
}

export default App;
