package org.sang.cache.controller;

import org.sang.cache.mybook.MyBook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyBookController {


    @GetMapping("/myBook")
    public MyBook getMyBook(){
        MyBook myBook = new MyBook();
        myBook.setId(1);
        myBook.setName("十分钟冥想");
        myBook.setAuthor("安迪");

        return myBook;
    }
}
