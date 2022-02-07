package org.sang.controller;

import org.sang.model.Book;
import org.sang.mapper1.BookMapper;
import org.sang.mapper2.BookMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookMapper2 bookMapper2;

    @GetMapping("/test1")
    public void test1(){
        List<Book> books1 = bookMapper.getAllBooks();
        List<Book> books2 = bookMapper2.getAllBooks();
        System.out.println("book1:" + books1);
        System.out.println("book2:" + books2);
    }
}
