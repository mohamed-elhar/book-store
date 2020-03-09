package com.sqli.BookStore;


import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqli.BookStore.entities.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookStoreApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private int bookId = 1;

    @Test
    public void test_1_should_search_book_by_key() throws Exception {

        ResultActions resultActions = mvc.perform(get("/book/search/Robert")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].length()", greaterThan(0)));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<Book> responseBooks = Arrays.asList(objectMapper.readValue(contentAsString, Book[].class));
        bookId = responseBooks.stream().findFirst().get().getId();
    }

    @Test
    public void test_2_should_find_book_copy_availability() throws Exception {

        mvc.perform(get(new StringBuilder().append("/book/").append(bookId).append("/check").toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    public void test_3_should_not_find_book_copy_availability() throws Exception {

        mvc.perform(get(new StringBuilder().append("/book/").append(6).append("/check").toString()))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void test_4_should_find_book_copy() throws Exception {
        mvc.perform(get(new StringBuilder().append("/bookCopy/").append(1).append("/find").toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void test_5_should_throw_exception_not_found_book_copy() throws Exception {
        mvc.perform(get(new StringBuilder().append("/bookCopy/").append(2).append("/find").toString()))
                .andExpect(status().is4xxClientError());


    }

}