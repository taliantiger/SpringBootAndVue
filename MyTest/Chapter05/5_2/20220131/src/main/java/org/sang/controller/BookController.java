package org.sang.controller;

import com.google.gson.Gson;
import org.sang.model.Book;
import org.sang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/bookOps")
    public void bookOps(){
        Book b1 = new Book();
        b1.setName("西厢记");
        b1.setAuthor("王实甫");
        int i = bookService.addBook(b1);
        System.out.println("addBook>>>" + i);
        List<Book> allBooks = bookService.getAllBooks();
        System.out.println("getAllBooks__first>>>" + allBooks);

        Book b2 = new Book();
        b2.setId(1);
        b2.setName("朝花夕拾");
        b2.setAuthor("鲁迅");
        int updateBook = bookService.updateBook(b2);
        System.out.println("updateBook>>>" +updateBook );

        Book b3 = bookService.getBookById(1);
        System.out.println("getBookById>>>" + b3);

        int delete = bookService.deleteBookById(2);
        System.out.println("deleteBookById>>>" + delete);

        List<Book> allBooks2 = bookService.getAllBooks();

//        如果重写了Bean 对象的toString()方法
//        就不需要Gson了
//        Gson gson = new Gson();
//        String str = gson.toJson(allBooks);
//        System.out.println("getAllBooks>>>" + str);

        System.out.println("getAllBooks___second>>>" + allBooks2);
    }

}
