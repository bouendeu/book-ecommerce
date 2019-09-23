package com.itbcafrica.repository;

import com.itbcafrica.Book;
import com.itbcafrica.Language;
import com.itbcafrica.rest.BookEndPoint;
import com.itbcafrica.rest.JAXRSConfiguration;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by OEM on 21.09.2019.
 */
@RunWith(Arquillian.class)
@RunAsClient
public class BookEndpointTestTest {

    private static String bookId;
    private Response response;

//    @Test
//    public void shouldGetNoBook(@ArquillianResteasyResource("api/books") WebTarget webTarget) {
//        // Count all
//        response = webTarget.path("count").request().get();
//        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
//        // Find all
//        response = webTarget.request(MediaType.APPLICATION_JSON).get();
//        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
//        Book book = new Book("ejb", "java EE", 12F, "131312", new Date(), 123, Language.DEUTSCH, "babab");
//        response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(book, MediaType.APPLICATION_JSON));
//        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
//    }

    @Deployment(testable = false)
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookRepository.class)
                .addClass(Book.class)
                .addClass(TextUtil.class)
                .addClass(Language.class)
                .addClass(BookEndPoint.class)
                .addClass(JAXRSConfiguration.class)
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}