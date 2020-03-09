package com.sqli.BookStore.endpoint;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sqli.BookStore.entities.Book;
import com.sqli.BookStore.entities.BookCopy;
import com.sqli.BookStore.entities.Location;
import com.sqli.BookStore.entities.Store;

public class StoreTestFactory {

    public static Store createTestStore() {
        Book martinBook = new Book(1, "Robert C. martin", "clean code");
        List<BookCopy> bookscopies = Stream.of(
                new BookCopy(1, martinBook, new Location("IT 5", "4", "LEFT")),
                new BookCopy(2, martinBook, new Location("IT 6", "2", "RIGHT")))
                .collect(Collectors.toList());
        return new Store(bookscopies);
    }

}
