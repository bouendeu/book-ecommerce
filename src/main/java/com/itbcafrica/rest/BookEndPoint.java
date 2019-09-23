package com.itbcafrica.rest;

import com.itbcafrica.Book;
import com.itbcafrica.repository.BookRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by OEM on 23.09.2019.
 */
@Path(("/books"))
public class BookEndPoint {

    @Inject
    private BookRepository bookRepository;

    public Book getBook(Long id) {
        return bookRepository.find(id);
    }

    public Book createBook(Book book) {
        return bookRepository.create(book);
    }

    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }

    @GET
    @Produces("application/json")
    public Response getBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.size() == 0)
            return Response.noContent().build();
        return Response.ok(books).build();
    }

    @GET
    @Path("/count")
    public Response countBooks() {
        Long aLong = bookRepository.countAll();
        if (aLong == 0) {
            return Response.noContent().build();
        }
        return Response.ok(aLong).build();
    }

    @GET
    @Produces("application/json")
    @Path("/id:\\d+")
    public Response getBookByIds(@PathParam("id")Long id) {
        Book book = bookRepository.find(id);
        if (book == null)
            return Response.noContent().build();
        return Response.ok(book).build();
    }
}
