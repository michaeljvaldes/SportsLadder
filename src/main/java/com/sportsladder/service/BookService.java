package com.sportsladder.service;

import com.sportsladder.domain.Book;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Felipe Leite on 7/1/2017.
 */
public interface BookService {
    public List<Book> findAll();
    public void saveBook(Book book);
    public Book findOne(long id);
    public void delete(long id);
    public List<Book> findByName(String name);
    public List<Book> findByNameAndAuthor(String name, String author);
    public List<Book> findByPrice(long price);
}