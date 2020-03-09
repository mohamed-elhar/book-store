package com.sqli.BookStore.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.ToString;
@ToString
@Entity
public class BookCopy {

	@Id
    private  int id;

	@OneToOne
    private  Book book;

	@OneToOne
    private  Location location;

    @OneToOne
    private Customer customer;

    private boolean available;
    
    public BookCopy() {
	}

    public boolean isAvailable() {
        return available;
    }

    public BookCopy(int id, Book book, Location location) {
        this.id = id;
        this.book = book;
        this.location = location;
        available=true;
    }

    public Book getBook() {
        return book;
    }

    public Location getLocation() {
        return location;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
