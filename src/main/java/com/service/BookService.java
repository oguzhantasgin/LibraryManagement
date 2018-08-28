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
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
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

        Book book = null;
        Integer bookId = null;


        if (jsonObject.has("bookId") && jsonObject.get("bookId") != null && !jsonObject.getString("bookId").equals(""))
            bookId = jsonObject.getInt("bookId");


        if (bookId != null) {
            book = (Book) bookDAO.loadObject(Book.class, bookId);
        }
        else {
            book = new Book();
        }


        int bookNumber = jsonObject.getInt("number");
        String bookName = jsonObject.getString("name");
        String bookAuthor = jsonObject.getString("author");
        String strBookYear = jsonObject.getString("year");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        Date bookDate = sdf.parse(strBookYear);



//////////////////////////////////////////////////////////////////////////////////////



        Publisher publisher = null;
        Integer publisherId = null;


        if (jsonObject.has("publisherId") && jsonObject.get("publisherId") != null && !jsonObject.getString("publisherId").equals(""))
            publisherId = jsonObject.getInt("publisherId");


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
    public JSONObject deleteBook(Integer bookId) {
        Book book = (Book) bookDAO.loadObject(Book.class, bookId);
        boolean success = bookDAO.removeObject(book);

        JSONObject jsonReturn = new JSONObject();
        jsonReturn.put("success", success);
        return jsonReturn;


    }


}
