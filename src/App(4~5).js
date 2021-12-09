import logo from './logo.svg';
import './App.css';

// 0. React 엔진 - 데이터변경감지에서 UI그려주는!!
// 1. 실행방식 (index.html) - SPA
// 2. JSX 문법

//(1) return시에 하나의 Dom만 리턴할 수 있다.
//(2) 변수선언은 let 혹은 const로만 할 수 있다.
//(3) if사용 불가능X => 삼항연산자 사용가능 (조건 ? 값(ture) : 값(false) )
//(4) 조건부 렌더링 => ( 조건 && 값(true) )
/*(5) css 디자인 
   - 내부에 적는 방법
   - 외부 파일에 적는 방법
   - 라이브러리 사용(부트스트랩,component-styled)
*/

function App() {
   let list = [1, 2, 3];

   return (
      <div>
         <div>
            {list.map((n) => {
               return <h1>{n}</h1>;
            })}
         </div>
      </div>
   );
}

export default App;
