package com.sqli.BookStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.BookStore.entities.BookCopy;

public interface CopyBookRepository extends JpaRepository<BookCopy, Integer>{

}
