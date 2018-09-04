

package dao;

import com.config.AppConfig;
import com.config.WebAppInitializer;
import com.config.WebConfig;
import com.dao.BookDAO;
import com.dao.PublisherDAO;
import com.model.library.Book;

import com.model.library.Publisher;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, WebAppInitializer.class})
@Transactional
@WebAppConfiguration
public class TestBookDAO {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private PublisherDAO publisherDAO;

    @Ignore
    public void testLoadBooks() {
        List<Book> bookList = bookDAO.loadBooks();
        Assert.assertEquals(1, bookList.size());

    }

    @Ignore
    @Transactional
    @Rollback(true)
    public void testAddBook() throws Exception {

        //Set test publisher and variables

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Oguzhan");
        publisher.setPublisherAddress("Taşgın");
        publisherDAO.saveOrUpdateObject(publisher);
        Book book = new Book();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date testBookDate = new Date();
        dateFormat.format(testBookDate);
        Long testBookNumber = 23L;
        book.setPublisher(publisher);
        book.setBookName("BOOK NAME");
        book.setAuthor("AUTHOR");
        book.setBookNumber(testBookNumber);
        book.setYear(testBookDate);


        ///////////////////////

        bookDAO.saveOrUpdateObject(book);
        List<Book> books = bookDAO.loadBooks();
        Assert.assertEquals(1, books.size());

    }

    @Transactional
    @Ignore
    @Rollback(true)
    public void testDeleteBook() throws Exception {

        Book book = (Book) bookDAO.loadObject(Book.class, 7L);
        bookDAO.removeObject(book);
        Book testBook = (Book) bookDAO.loadObject(Book.class, 7L);
        Assert.assertNull(testBook);

    }

    @Ignore
    public void testLoadBookById() throws Exception {
        Book book = bookDAO.loadBookById(1L);
        Assert.assertNotNull(book);
    }

}