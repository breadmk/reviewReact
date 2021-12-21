package com.breadmk.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.breadmk.book.domain.Book;
import com.breadmk.book.domain.BookRepository;

import lombok.RequiredArgsConstructor;

// 기능을 정의할 수 있고, 트랜잭션을 관리할 수 있음.

@RequiredArgsConstructor   ///final 이 붙어있는애들의 생성자를 만들어줌.
@Service
public class BookService {

	//함수 => 송금() -> 레파지토리에 여러개의 함수를 실행하고 -> Commit or Rollback
	
	private final BookRepository bookRepository;
	
	@Transactional   //서비스 함수가 종료될 때 commit 할지 rollback 할지 트랜잭션 관리하겠다.
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
																	//update시의 정합성을 유지해줌. insert의 유령데이터현상(팸텀현상) 못막음.
	@Transactional(readOnly = true) // JPA 변경감지라는 내부 기능이 있음 readOnly를 줘야 이 기능이 꺼져서 성능이 향상됨.
	public Book findOne(Long id) {
		return bookRepository.findById(id) //한건만 찾는건데 못 찾는 경우가 있을수 있어서 Exception 처리를 해줘야한다.
				.orElseThrow(()->new IllegalArgumentException("id를 확인해주세요!"));
	}
	
	@Transactional(readOnly = true)
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	@Transactional
	public Book update(Long id, Book book) {
		// 더티체킹 update치기
		Book bookEntity = bookRepository.findById(id)													//영속성 컨텍스트 보관
				.orElseThrow(()->new IllegalArgumentException("id를 확인해주세요!")); //영속화 (book 오브젝트) //스프링이 들고 있음!!! 그걸 토대로 비교해서 update 치자
		bookEntity.setTitle(book.getTitle());
		bookEntity.setAuthor(book.getAuthor());
		return bookEntity;
	}// 함수 종료 =>  트랜잭션 종료 => 영속화 되어있는 데이터를 DB로 갱신(flush) => commit ==========> 더치체킹
	
	@Transactional
	public String delete(Long id) {
		bookRepository.deleteById(id); //오류가 나면 excetion을 타고 그건 따로 관리 해주고 ok 떨어지면 완료.
		return "ok";
	}
}










