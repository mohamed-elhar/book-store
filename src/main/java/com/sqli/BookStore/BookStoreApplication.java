package com.sqli.BookStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sqli.BookStore.dao.BookRepository;
import com.sqli.BookStore.dao.CopyBookRepository;
import com.sqli.BookStore.dao.LocationRepository;
import com.sqli.BookStore.entities.Book;
import com.sqli.BookStore.entities.BookCopy;
import com.sqli.BookStore.entities.Location;

@SpringBootApplication
public class BookStoreApplication implements ApplicationRunner {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CopyBookRepository copyBookRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Location location = new Location("A1", "1", "LEFT");
		locationRepository.save(location);
		
		bookRepository.save(new Book(1, "A", "A"));
		bookRepository.save(new Book(2, "B", "B"));
		bookRepository.save(new Book(3, "C", "C"));

		bookRepository.findAll().forEach(book -> 
		copyBookRepository.save(new BookCopy(book.getId(), book, location)));
		
		
		bookRepository.findAll().forEach(System.out::println);
		copyBookRepository.findAll().forEach(System.out::println);
	}
	
	

}
