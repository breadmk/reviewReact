package com.breadmk.book.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.breadmk.book.domain.Book;
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
} 












