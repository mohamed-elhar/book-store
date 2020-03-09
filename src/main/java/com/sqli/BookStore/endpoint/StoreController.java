package com.sqli.BookStore.endpoint;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.BookStore.entities.Book;
import com.sqli.BookStore.entities.BookCopy;
import com.sqli.BookStore.service.BookStoreService;
import com.sqli.BookStore.service.impl.BookStoreServiceImpl;

@RestController
public class StoreController {

//	private BookStoreService bookStoreService = new BookStoreServiceImpl(StoreTestFactory.createTestStore());
	
	@Autowired
	private BookStoreService bookStoreService ;

	@GetMapping("/book/search/{key}")
	@ResponseBody
	public Set<Book> search(@PathVariable("key") String key) {
		return bookStoreService.findBooksByKey(key);
	}

	@GetMapping("/book/{id}/check")
	public boolean checkStock(@PathVariable int id) {
		return bookStoreService.checkStock(id);
	}
	
	@GetMapping("/bookCopy/{id}/find")
	public BookCopy findBookCopy (@PathVariable int id) {
		return bookStoreService.findCopyInStore(id);
	}
}
