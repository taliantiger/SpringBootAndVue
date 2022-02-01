package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.model.Book;

import java.util.List;

// @Mapper 表明该接口是一个MyBatis 中的Mapper
@Mapper
public interface BookMapper {
    int addBook(Book book);
    int deleteBookById(Integer id);
    int updateBookById(Book book);
    Book getBookById(Integer id);

    List<Book> getAllBooks();

}
