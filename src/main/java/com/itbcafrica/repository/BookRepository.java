package com.itbcafrica.repository;

import com.itbcafrica.Book;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by OEM on 21.09.2019.
 */
@Transactional(Transactional.TxType.SUPPORTS)
public class BookRepository {
    @PersistenceContext(unitName = "bookstorePU")
    private EntityManager entityManager;

    @Inject
    private TextUtil textUtil;

    public Book find(@NotNull Long id){
        Book book = entityManager.find(Book.class, id);
        return  book;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public  Book create(@NotNull Book book){

        book.setTitle(textUtil.sanitize(book.getTitle()));
        entityManager.persist(book);
        return book;
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public  void delete(@NotNull Long id){
        Book reference = entityManager.getReference(Book.class, id);
        entityManager.remove(reference);
    }
    public List<Book> findAll(){
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b order by b.title desc ", Book.class
        );
        return query.getResultList();
    }
    public  Long  countAll(){
        TypedQuery<Long> query = entityManager.createQuery("select  count (b) from Book b", Long.class);
        return  query.getSingleResult();

    }
}
