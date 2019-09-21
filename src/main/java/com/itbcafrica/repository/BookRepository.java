package com.itbcafrica.repository;

import com.itbcafrica.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by OEM on 21.09.2019.
 */
public class BookRepository {
    @PersistenceContext(unitName = "bookstorePU")
    private EntityManager entityManager;

    public Book find(Long id){
        Book book = entityManager.find(Book.class, id);
        return  book;
    }
    public  Book create(Book book){

        entityManager.persist(book);
        return book;
    }
    public  void delete(Long id){
        Book reference = entityManager.getReference(Book.class, id);
        entityManager.remove(reference);
    }
}
