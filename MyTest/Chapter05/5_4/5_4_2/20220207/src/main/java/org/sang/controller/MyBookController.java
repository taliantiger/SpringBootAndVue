package org.sang.controller;


import org.sang.mybook.MyBook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyBookController {
    @GetMapping("/myBook")
    public MyBook BookTest(){
        MyBook myBook = new MyBook();
        myBook.setId(1);
        myBook.setAuthor("安迪");
        myBook.setName("十分钟冥想");

        return myBook;
    }
}
