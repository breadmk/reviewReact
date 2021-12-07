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

let a = 10; // 변수
const b = 20; // 상수

function App() {
	let c;
	let d = undefined;
	console.log(1, c);

	const mystyle = {
		color: 'red'
	};

	return (
		<div>
			<div style={mystyle}>안녕{a === 10 ? '10입니다' : '10이 아닙니다.'}</div>
			<h1 className='box-style'>해딩태크{b === 10 && '20입니다.'}</h1>
			<hr />
		</div>
	);
}

export default App;
