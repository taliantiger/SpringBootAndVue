package org.sang.mapper1;

import org.apache.ibatis.annotations.Mapper;
import org.sang.model.Book;

import java.util.List;

/**
 * 不写Mapper，则BookController.class 中的BookMapper变量，
 *  无法通过Autowired自动装配，
 *  找到作为的 Bean 对象的 BookMapper 接口
 *
 *  ===》变量无法自动装配到接口
 */
@Mapper
public interface BookMapper {
    List<Book> getAllBooks();
}



