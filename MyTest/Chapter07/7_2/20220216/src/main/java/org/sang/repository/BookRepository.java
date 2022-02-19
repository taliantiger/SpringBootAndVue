package org.sang.repository;

import org.sang.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

// 自定义请求路径
@RepositoryRestResource(path = "bs", collectionResourceRel = "bs", itemResourceRel = "b")

public interface BookRepository extends JpaRepository<Book, Integer> {
    
}
