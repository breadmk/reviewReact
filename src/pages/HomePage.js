import React, { useEffect } from 'react';
import { useState } from 'react/cjs/react.development';
import Footer from '../components/Footer';
import Header from '../components/Header';
import Home from '../components/home/Home';

const HomePage = () => {
	// http 요청(jquery ajax(사용x),fetch,axios(다운))
	const [ boards, setBoards ] = useState([]);

	// 빈 배열 한번만 실행.
	useEffect(() => {
		//다운로드 가정 = fetch(),axios(),ajax()
		let data = [
			{ id: 1, title: '제목1', content: '내용1' },
			{ id: 2, title: '제목2', content: '내용2' },
			{ id: 3, title: '제목3', content: '내용3' }
		];
		setBoards([ ...data ]);
	}, []);

	return (
		<div>
			<Header />
			<Home boards={boards} />
			<Footer />
		</div>
	);
};

export default HomePage;
