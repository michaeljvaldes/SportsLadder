package com.sportsladder.dataaccess;

import com.sportsladder.domain.Book;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
/**
 * Created by Felipe Leite on 7/1/2017.
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByName(String name);
    List<Book> findByPrice(long price);
    List<Book> findByNameAndAuthor(String name, String author);
    List<Book> findAll();
}
