package org.sang.controller;

import org.sang.mybook.MyBook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyBookController {
    @GetMapping("/myBook")
    public MyBook BookTest(){
        MyBook myBook = new MyBook();
        myBook.setName("三国演义");
        myBook.setAuthor("罗贯中");

        return myBook;
    }
}
