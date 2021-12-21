package com.breadmk.book.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

//단위 테스트 => DB 관련된 Bean이 메모리에 띄우면 됨.
//내장 DB로 테스트 (실제DB (X)), 가짜 DB로 테스트  ////// Replace.NONE 실제 DB로 테스트
@AutoConfigureTestDatabase(replace = Replace.ANY) 
@DataJpaTest //jpa에 관련된 애들만 메모리에 올라옴  // Repository 들을 모두 IoC에 등록해줌.
@Transactional
public class BookRepositoryUnitTest {

	@Autowired
	private BookRepository bookRepository; // @DataJpaTest 안에 스프링 환경으로 확장되어서 Mock로 띄울 필요가 없음.
}
