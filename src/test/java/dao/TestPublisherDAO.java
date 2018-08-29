package dao;

import com.config.AppConfig;
import com.config.WebAppInitializer;
import com.config.WebConfig;
import com.dao.PublisherDAO;
import com.model.Publisher;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class, AppConfig.class, WebConfig.class})
@Transactional
@WebAppConfiguration
public class TestPublisherDAO {

    @Autowired
    private PublisherDAO publisherDAO;

    @Test
    public void testLoadPublishers() {
        List<Publisher> publisherList = publisherDAO.loadPublishers();
        Assert.assertTrue(publisherList.size() > 0);

    }


    @Test
    @Transactional
    @Rollback(true)
    public void testAddPublisher() {


        for (int i = 0; i < 2; i++) {

            Publisher publisher = new Publisher();
            publisher.setPublisherName("ASD");
            publisher.setPublisherAddress("ASD");
            publisherDAO.saveOrUpdateObject(publisher);
        }


        List<Publisher> publishers = publisherDAO.loadPublishers();
        Assert.assertEquals(2, publishers.size());


    }


    @Test
    public void testLoadPublisherById() {


        Publisher publisher = publisherDAO.loadPublisherById(1L);
        Assert.assertNotNull(publisher);


    }


    @Test
    @Transactional
    @Rollback(false)
    public void testDeletePublisher() {

        Publisher book = (Publisher) publisherDAO.loadObject(Publisher.class, 1L);
        publisherDAO.removeObject(book);
        Publisher testPublisher = (Publisher) publisherDAO.loadObject(Publisher.class, 1L);
        Assert.assertNull(testPublisher);

    }

}
