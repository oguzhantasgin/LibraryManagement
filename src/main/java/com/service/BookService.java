package com.service;
//Package imports

import com.dao.BookDAO;
import com.dao.PublisherDAO;
import com.model.Book;


import com.model.Publisher;
import org.springframework.transaction.annotation.Propagation;
import com.util.DateJSONValueProcessor;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BookService {

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private PublisherDAO publisherDAO;

    public JSONObject loadBooks() {

        List<Book> bookList = bookDAO.loadBooks();
        JsonConfig jsonConfig = new JsonConfig();
        DateJSONValueProcessor dateProc = new DateJSONValueProcessor("dd/MM/yyyy");
        jsonConfig.registerJsonValueProcessor(Date.class, dateProc);
        JSONArray jsonArray = JSONArray.fromObject(bookList, jsonConfig);

        for (int i = 0; i < jsonArray.size(); i++) {

            Book book = bookList.get(i);
            JSONObject json = jsonArray.getJSONObject(i);

            Publisher publisher = book.getPublisher();

            if (publisher != null) {
                json.put("publisherId", publisher.getPublisherId());
                json.put("publisherName", publisher.getPublisherName());
            }

        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        jsonObject.put("success", true);
        jsonObject.put("totalCount", jsonArray.size());
        return jsonObject;

    }

    @Transactional
    public JSONObject saveOrUpdateBook(JSONObject jsonObject) throws ParseException {


        //Clear hype

        Book book = null;
        Long bookId = null;

        // If JSON object has bookId value, then retrieve it

        if (jsonObject.has("bookId") && jsonObject.get("bookId") != null && !jsonObject.getString("bookId").equals(""))
            bookId = jsonObject.getLong("bookId");

        //Value of a bookId tells us what will do, update or insert ?

        if (bookId != null) {
            book = (Book) bookDAO.loadObject(Book.class, bookId);
        } else {
            book = new Book();
        }


        Long bookNumber = jsonObject.getLong("bookNumber");
        String bookName = jsonObject.getString("bookName");
        String bookAuthor = jsonObject.getString("bookAuthor");
        Date bookDate = dateConverter(jsonObject.getString("bookYear"));

        Publisher publisher = null;
        Long publisherId = null;


        if (jsonObject.has("publisherId") && jsonObject.get("publisherId") != null && !jsonObject.getString("publisherId").equals(""))
            publisherId = jsonObject.getLong("publisherId");


        if (publisherId != null) {
            publisher = (Publisher) publisherDAO.loadObject(Publisher.class, publisherId);

        }


        book.setAuthor(bookAuthor);
        book.setBookNumber(bookNumber);
        book.setYear(bookDate);
        book.setBookName(bookName);
        book.setPublisher(publisher);

        boolean success = bookDAO.saveOrUpdateObject(book);

        JSONObject jsonReturn = new JSONObject();
        jsonReturn.put("success", success);
        return jsonReturn;


    }

    @Transactional
    public JSONObject deleteBook(Long bookId) {

        Book book = (Book) bookDAO.loadObject(Book.class, bookId);
        boolean success = bookDAO.removeObject(book);
        JSONObject jsonReturn = new JSONObject();
        jsonReturn.put("success", success);
        return jsonReturn;

    }

    public Date dateConverter(String stringDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        return sdf.parse(stringDate);
    }
}
