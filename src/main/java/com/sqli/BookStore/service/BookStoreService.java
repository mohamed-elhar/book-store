package com.sqli.BookStore.service;

import java.util.Set;

import com.sqli.BookStore.entities.Book;
import com.sqli.BookStore.entities.BookCopy;
import com.sqli.BookStore.entities.Customer;

public interface BookStoreService {

    Set<Book> findBooksByKey(String key);

    boolean checkStock(int bookId);

    BookCopy findCopyInStore(int bookId);

    void confirmOrder(BookCopy copy, Customer customer);
}
