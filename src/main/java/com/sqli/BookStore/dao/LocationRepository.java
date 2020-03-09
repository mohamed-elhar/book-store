package com.sqli.BookStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.BookStore.entities.Location;

public interface LocationRepository extends JpaRepository<Location, String>{

}
