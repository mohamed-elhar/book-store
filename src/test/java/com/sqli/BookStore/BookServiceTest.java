package com.sqli.BookStore;


import org.junit.Before;
import org.junit.Test;

import com.sqli.BookStore.entities.Book;
import com.sqli.BookStore.entities.BookCopy;
import com.sqli.BookStore.entities.Customer;
import com.sqli.BookStore.service.impl.BookStoreServiceImpl;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class BookServiceTest {

    private BookStoreServiceImpl bookStoreService;

    @Before
    public void setUp() {
        bookStoreService = new BookStoreServiceImpl(StoreTestFactory.createTestStore());
    }

    @Test
    public void should_find_book_by_name() {
        Set<Book> books = bookStoreService.findBooksByKey("clean code");
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    public void should_find_book_by_author_name() {
        Set<Book> books = bookStoreService.findBooksByKey("Robert C");
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void should_not_find_book_by_author_name() {
        Set<Book> books = bookStoreService.findBooksByKey("dummy");
        bookStoreService.findCopyInStore(books.stream().findFirst().get().getId());
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    public void should_find_book_check_stock() {
        Set<Book> books = bookStoreService.findBooksByKey("Robert C");
        boolean exists = bookStoreService.checkStock(books.stream().findFirst().get().getId());
        assertNotNull(books);
        assertTrue(exists);
    }

    @Test
    public void should_find_available_book_copy_and_location() {
        Set<Book> books = bookStoreService.findBooksByKey("Robert C");
        BookCopy copy = bookStoreService.findCopyInStore(books.stream().findFirst().get().getId());
        assertNotNull(copy);
        assertTrue(copy.isAvailable());
        assertNotNull(copy.getLocation());
    }

    @Test
    public void should_confirm_order_and_assigne_copy_to_customer() {
        Customer customer = new Customer(1,"A","A");
        Set<Book> books = bookStoreService.findBooksByKey("Robert C");
        BookCopy copy = bookStoreService.findCopyInStore(books.stream().findFirst().get().getId());
        bookStoreService.confirmOrder(copy, customer);
        assertFalse(copy.isAvailable());
    }


    @Test(expected = RuntimeException.class)
    public void should_throw_exception_no_available_book_copy() {
        Set<Book> books = bookStoreService.findBooksByKey("Robert C");
        BookCopy copy = bookStoreService.findCopyInStore(books.stream().findFirst().get().getId());
        bookStoreService.confirmOrder(copy, new Customer(1,"A","A"));
        bookStoreService.confirmOrder(copy, new Customer(1,"A","A"));
        assertNotNull(copy);
    }
}