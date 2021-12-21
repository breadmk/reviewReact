package com.breadmk.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;


//@Repository 를 적어야 스프링 IoC에 Bean으로 등록이 되는데, JpaRepository 를 extends 하면 생략이 가능함.
//JpaRepository CRUD 함수를 들고 있음.
public interface BookRepository extends JpaRepository<Book, Long>{

}
