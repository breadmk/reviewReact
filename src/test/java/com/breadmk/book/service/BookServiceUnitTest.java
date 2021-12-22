package com.breadmk.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.breadmk.book.domain.Book;
import com.breadmk.book.domain.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
* 단위 테스트 => Service와 관련된 Bean이 메모리에 띄우면 됨.
* BoardRepository => 가짜 객체로 만들 수 있음.
**/
@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {
	
//	@Mock => Mockito 라는 메모리 공간에 올라감. (가짜로 올라감)
	
	@InjectMocks // BookService 객체가 만들어질 때 해당 파일에 @Mock로 등록된 모은 애들을 주입받는다.
	private BookService bookService;
	@Mock
	private BookRepository bookRepository;
	
	@Test
	public void save_test() throws Exception {
	
	//given
	Book book = new Book();
	book.setTitle("책제목");
	book.setAuthor("책저자");
	
	when(bookRepository.save(book)).thenReturn(book);
	
	//then
	Book bookEntity = bookService.save(book);
	
	//then
	assertEquals(bookEntity, book);
	
	}
}
