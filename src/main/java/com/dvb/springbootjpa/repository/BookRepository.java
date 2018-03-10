package com.dvb.springbootjpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dvb.springbootjpa.model.Book;

@Repository
public interface BookRepository extends	CrudRepository<Book, Long>{
	
	List<Book> findByTitle(String title);
	List<Book> findByAuthorAndPublisher(String author, String publisher);

}
