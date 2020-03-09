package com.sqli.BookStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.BookStore.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
