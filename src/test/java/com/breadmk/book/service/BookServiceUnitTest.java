package com.breadmk.book.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.breadmk.book.domain.BookRepository;

/**
* 단위 테스트 => Service와 관련된 Bean이 메모리에 띄우면 됨.
* BoardRepository => 가짜 객체로 만들 수 있음.
**/
@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {
	
//	@Mock => Mockito 라는 메모리 공간에 올라감. (가짜로 올라감)
	
	@Mock
	@InjectMocks // BookService 객체가 만들어질 때 해당 파일에 @Mock로 등록된 모은 애들을 주입받는다.
	private BookService bookService;
	@Mock
	private BookRepository bookRepository;
}
