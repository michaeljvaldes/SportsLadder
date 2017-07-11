package com.sportsladder.service;

import com.sportsladder.dataaccess.BookRepository;
import com.sportsladder.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Felipe Leite on 7/1/2017.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public List<Book> findByName(String name){
        return bookRepository.findByName(name);
    }
    public List<Book> findByPrice(long price){
        return bookRepository.findByPrice(price);
    }
    public List<Book> findByNameAndAuthor(String name, String author){
        return bookRepository.findByNameAndAuthor(name,author);
    }
    public void saveBook(Book book){
        bookRepository.save(book);
    }
    public Book findOne(long id){
        return bookRepository.findOne(id);
    }
    public void delete(long id){
        bookRepository.delete(id);
    }
}
