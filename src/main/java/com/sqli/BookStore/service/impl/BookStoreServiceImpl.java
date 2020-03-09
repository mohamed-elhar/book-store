package com.sqli.BookStore.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.sqli.BookStore.dao.CopyBookRepository;
import com.sqli.BookStore.entities.Book;
import com.sqli.BookStore.entities.BookCopy;
import com.sqli.BookStore.entities.Customer;
import com.sqli.BookStore.entities.Store;
import com.sqli.BookStore.service.BookStoreService;

public class BookStoreServiceImpl implements BookStoreService {

    private final Store store;
    
    @Autowired
    private CopyBookRepository copyBookRepository;

    public BookStoreServiceImpl(Store store) {
        this.store = store;
    }

    @Override
    public Set<Book> findBooksByKey(String key) {
        return  copyBookRepository.findAll().stream()
                .filter(copy -> copy.getBook().getAuthor().contains(key) ||
                        copy.getBook().getTitle().contains(key))
                .map(BookCopy::getBook).collect(Collectors.toSet());
    }

    @Override
    public boolean checkStock(int bookId) {
        return findAvailableCopy(bookId).isPresent();
    }

    @Override
    public BookCopy findCopyInStore(int bookId) {
        return findAvailableCopy(bookId)
                .orElseThrow( ()->new RuntimeException("Copy not found"));
    }

    @Override
    public void confirmOrder(BookCopy copy, Customer customer) {
        if(copy.isAvailable()) {
            copy.setCustomer(customer);
            copy.setAvailable(false);
        }else {
            throw new RuntimeException("no copy is available");
        }
    }

    private Optional<BookCopy> findAvailableCopy(int bookId)
    {
        return copyBookRepository.findAll().stream()
                .filter(copy -> copy.getBook().getId()==bookId && copy.isAvailable()).findAny();
    }
}