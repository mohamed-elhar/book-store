package com.sqli.BookStore.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.BookStore.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Set<Customer> findByLastname(String lastname);
}
