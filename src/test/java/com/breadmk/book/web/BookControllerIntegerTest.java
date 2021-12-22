package com.breadmk.book.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.breadmk.book.domain.Book;
import com.breadmk.book.domain.BookRepository;
import com.breadmk.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/*
 통합 테스트 - > 모든 Bean 들을 똑같이 IoC 올리고 테스트 하는 것.
 통합 테스트란 컨트롤러로 전체 스프링 내 프로젝트를 테스트 한다는 것. 
 WebEnvironment.RANDOM_PORT  = 실제 톰켓으로 테스트
 @AutoConfigureMockMvc  -> MockMvc를 IoC에 등록해줌.
 @Transactional 은 각 각의 테스트 함수가 종료될 때마다 트랜잭션을 rollback 해주는 어노테이션!
*/
@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) //실제 톰캣을 돌리는게 아니라, 다른 톰켓으로 테스트
public class BookControllerIntegerTest {

    @Autowired
	private MockMvc mockMvc;
    
    @Autowired
    private BookRepository bookRepository;
    
    @BeforeEach
    public void init() {
    	
    }
    
	//DDDMockito 패턴  given, when, then
	@Test
	public void save_test() throws Exception {
		log.info("save_Test() 시작 =======================");
		
		//given (테스트를 하기 위한 준비)
		Book book = new Book(null,"스프링따라하기","cos");
		String content = new ObjectMapper().writeValueAsString(new Book(null,"스프링따라하기","cos")); //Object를 Json으로 바꾸는 함수.
		
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
	
	@Test
	public void findAll_test() throws Exception{
		
		//given || 더미데이터 생성 //통합테스트에서는 아래가 필요없음..실제 디비로 할꺼니까
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(1L,"스프링부트 따라하기","cos"));
		books.add(new Book(2L,"리액트 따라하기","cos"));
		books.add(new Book(3L,"junit 따라하기","cos"));
		bookRepository.saveAll(books);
		
		
		//when
		ResultActions resulitAction = mockMvc.perform(get("/book")
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resulitAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$",Matchers.hasSize(3)))
			.andExpect(jsonPath("$.[2].title").value("junit 따라하기"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	public void findById_test() throws Exception{
		//given
		Long id =3L;

		List<Book> books = new ArrayList<Book>();
		books.add(new Book(1L,"스프링부트 따라하기","cos"));
		books.add(new Book(2L,"리액트 따라하기","cos"));
		books.add(new Book(3L,"junit 따라하기","cos"));
		bookRepository.saveAll(books);
		
		//when
		ResultActions resultAction = mockMvc.perform(get("/book/{id}",id)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("junit 따라하기"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	public void update_test() throws Exception{
		//given
		Long id =3L;
		
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(1L,"스프링부트 따라하기","cos"));
		books.add(new Book(2L,"리액트 따라하기","cos"));
		books.add(new Book(3L,"junit 따라하기","cos"));
		bookRepository.saveAll(books);
		
		Book book = new Book(null,"C++ 따라하기","cos");
		String content = new ObjectMapper().writeValueAsString(book); //Object를 Json으로 바꾸는 함수.
		
		//when
		ResultActions resultAction =  mockMvc.perform(put("/book/{id}",id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(3L))
			.andExpect(jsonPath("$.title").value("C++ 따라하기"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void delete_test() throws Exception{
		//given
		Long id =1L;
		
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(1L,"스프링부트 따라하기","cos"));
		books.add(new Book(2L,"리액트 따라하기","cos"));
		books.add(new Book(3L,"junit 따라하기","cos"));
		bookRepository.saveAll(books);
		
		//when
		ResultActions resultAction =  mockMvc.perform(delete("/book/{id}",id)
				.contentType(MediaType.TEXT_PLAIN));		
		//then
		resultAction
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print());
		
		MvcResult requestResult = resultAction.andReturn();
		String result = requestResult.getResponse().getContentAsString();
		
		assertEquals("ok", result);
	}
} 












