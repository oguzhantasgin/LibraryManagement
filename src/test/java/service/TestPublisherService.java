package service;


import com.config.AppConfig;
import com.config.WebAppInitializer;
import com.config.WebConfig;
import com.dao.PublisherDAO;
import com.model.library.Publisher;
import net.sf.json.JSONArray;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, WebAppInitializer.class})
@WebAppConfiguration
@Transactional
public class TestPublisherService {

    @Autowired
    private PublisherDAO publisherDAO;

    @Ignore
    public void testLoadPublishersJson(){

        List<Publisher> publisherList = publisherDAO.loadPublishers();
        JSONArray jsonArray = JSONArray.fromObject(publisherList);

        Assert.assertTrue(jsonArray.size() != 0);


    }

}
