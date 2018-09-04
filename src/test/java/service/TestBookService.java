package service;


import com.config.AppConfig;
import com.config.WebAppInitializer;
import com.config.WebConfig;
import com.dao.BookDAO;
import com.model.library.Book;
import com.util.DateJSONValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, WebAppInitializer.class})
@WebAppConfiguration
@Transactional
public class TestBookService {

    @Autowired
    private BookDAO bookDAO;

    @Ignore
    public void testLoadBooksJson() {


        List<Book> bookList = bookDAO.loadBooks();

        for(int i = 0; i<bookList.size();i++){

            System.out.println(bookList.get(i).toString());

        }



        JsonConfig jsonConfig = new JsonConfig();
        DateJSONValueProcessor dateProc = new DateJSONValueProcessor("dd/MM/yyyy");
        jsonConfig.registerJsonValueProcessor(Date.class, dateProc);
        JSONArray jsonArray = JSONArray.fromObject(bookList, jsonConfig);
        Assert.assertTrue(jsonArray.size() != 0);


    }


}
