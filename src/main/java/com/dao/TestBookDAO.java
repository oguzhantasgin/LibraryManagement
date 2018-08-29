package com.dao;

import com.config.AppConfig;
import com.config.WebAppInitializer;
import com.config.WebConfig;
import com.model.Book;

import com.model.Publisher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class, AppConfig.class, WebConfig.class})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TestBookDAO {

    @Autowired
    private BookDAO bookDAO;


    @Autowired
    private PublisherDAO publisherDAO;

    @Test

    public void testLoadPublishers() {
        List<Book> bookList = bookDAO.loadBooks();
        Assert.assertTrue(bookList.size() > 0);

    }

    @Test
    @Transactional

    public void testAddBook() throws Exception {


        //Set test publisher

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Oguzhan");
        publisher.setPublisherAddress("Taşgın");


        //Set test variables

        Book book = new Book();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date testBookDate = new Date();
        dateFormat.format(testBookDate);
        Long bookNumber = 23L;


        book.setPublisher(publisher);
        book.setBookName("BOOK NAME");
        book.setAuthor("AUTHOR");
        book.setBookNumber(bookNumber);
        book.setYear(testBookDate);


        ///////////////////////

        bookDAO.saveOrUpdateObject(book);
        List<Book> books = bookDAO.loadBooks();
        List<Publisher> publishers = publisherDAO.loadPublishers();
        Assert.assertEquals(1, books.size());
        Assert.assertEquals(1, publishers.size());


    }


}