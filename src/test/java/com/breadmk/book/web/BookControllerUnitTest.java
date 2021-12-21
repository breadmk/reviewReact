package com.breadmk.book.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.breadmk.book.domain.Book;
import com.breadmk.book.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// 단위 테스트 => Controller 관련 로직만 뛰우기  || Filter, Contorller Advice 
// 단위 테스트란 Controller만 테스트 한다는 것.

//현재는 독립적인 상황인데, spring 이랑 동일한 환경을 만들주기 위해서 @ExtendWith(SpringExtension.class) 붙여줘야함 (junit5에는 포함하고 있음) 
@WebMvcTest // WebMvcTest를 붙이면 filter / controller advice / controller 가 메모리에 올라감.
@Slf4j
public class BookControllerUnitTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean  // IoC 환경에 Bean으로 등록됨. //가짜 환경에서 테스트 함.
	private BookService bookService;
	
	//DDDMockito 패턴  given, when, then
	@Test
	public void save_test() throws Exception {
		log.info("save_Test() 시작 =======================");
		Book bookTest = bookService.save(new Book(null,"제목","코스"));
		log.info("book = > " + bookTest);
		//----------------------------------
		
		//given (테스트를 하기 위한 준비)
		Book book = new Book(null,"스프링따라하기","cos");
		String content = new ObjectMapper().writeValueAsString(new Book(null,"스프링따라하기","cos")); //Object를 Json으로 바꾸는 함수.
		when(bookService.save(book)).thenReturn(new Book(1L,"스프링따라하기","cos")); //함수가 실행된 결과값을 미리 유추하여 적기.
		
		//when (테스트 실행)
		ResultActions resultAction =  mockMvc.perform(post("/book")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then( 테스트에 대한 기대감 {검증})
		resultAction
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.title").value("스프링따라하기"))  //$.변수명 = > $전체결과.변수명
			.andDo(MockMvcResultHandlers.print());
	}
}