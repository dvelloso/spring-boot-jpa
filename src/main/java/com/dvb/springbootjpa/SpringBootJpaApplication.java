package com.dvb.springbootjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dvb.springbootjpa.model.Book;
import com.dvb.springbootjpa.repository.BookRepository;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner{
	
	@Autowired
	private BookRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n\nCreating 3 books...");
		repo.save(new Book("Da vinci Code", "Dan Brown", "Doubleday", 454L));
		repo.save(new Book("Inferno", "Dan Brown", "Doubleday", 609L));
		repo.save(new Book("Rogue One", "Alexander Freed", "Hal Leonard Books", 352L));		
		readRecords();
		
		System.out.println("\nUpdating number of pages of Inferno");
		Book book = repo.findByTitle("Inferno").get(0);
		book.setPages(700L);
		repo.save(book);
		readRecords();
		
		System.out.println("\nFiltering only Dan Brow's books by Stream");
		List<Book> books = (List<Book>) repo.findAll();
		books.stream().filter(b -> b.getAuthor().equals("Dan Brown")).forEach(b -> System.out.println(b));
		
		System.out.println("\n\nFiltering by author and publisher");
		books = repo.findByAuthorAndPublisher("Dan Brown", "Doubleday");
		books.forEach(b -> System.out.println(b));
		
		System.out.println("\nDeleting Rogue One");
		book = repo.findByTitle("Rogue One").get(0);
		repo.delete(book);
		readRecords();		
		
		System.out.println("\n\n");
	}
	
	private void readRecords(){
		System.out.println("\nReading books...");
		Iterable<Book> books = repo.findAll();
		books.forEach(b -> System.out.println(b));
	}
	
	
}
