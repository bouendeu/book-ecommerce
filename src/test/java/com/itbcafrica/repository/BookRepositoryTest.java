package com.itbcafrica.repository;

import com.itbcafrica.Book;
import com.itbcafrica.Language;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.PersistenceContext;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by OEM on 21.09.2019.
 */
@RunWith(Arquillian.class)
public class BookRepositoryTest {

    @Inject
    private BookRepository bookRepository;


    @Test
    public void testCreate() throws Exception {

        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());
        Book book= new Book("ejb","java EE",12F,"131312",new Date(),123,Language.DEUTSCH,"babab");
        // create new book
        bookRepository.create(book);
        assertEquals(1, bookRepository.findAll().size());
        Long id = book.getId();
        Book bookdb= bookRepository.find(id);
        assertEquals("ejb",bookdb.getTitle());

        // delete the book
        bookRepository.delete(id);
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

    }

    @Test(expected = Exception.class)
    public void findWithInvalidId(){

      bookRepository.find(null);
    }


    @Test(expected = Exception.class)
    public void invalidBook(){
        Book book= new Book(null,"java EE",12F,"131312",new Date(),123,Language.DEUTSCH,"babab");
        // create new book
        bookRepository.create(book);
    }


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookRepository.class)
                .addClass(Book.class)
                .addClass(TextUtil.class)
                .addClass(Language.class)
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}