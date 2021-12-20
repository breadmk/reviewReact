import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import reducer from './store';
import { createStore } from 'redux';
import { Provider } from 'react-redux';

const store = createStore(reducer);

// 나의 모든 APP 에서 사용할 수 있도록 Provider로 감싸줘야 한다.
ReactDOM.render(
	<BrowserRouter>
		<Provider store={store}>
			<App />,
		</Provider>
	</BrowserRouter>,
	document.getElementById('root')
);

reportWebVitals();
