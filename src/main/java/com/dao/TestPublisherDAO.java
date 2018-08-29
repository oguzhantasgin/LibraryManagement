package com.dao;

import com.config.AppConfig;
import com.config.WebAppInitializer;
import com.config.WebConfig;
import com.model.Publisher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class, AppConfig.class, WebConfig.class})
@Transactional
public class TestPublisherDAO {

    @Autowired
    private PublisherDAO publisherDAO;

    @Test

    public void testLoadPublishers() {
        List<Publisher> publisherList = publisherDAO.loadPublishers();
        Assert.assertTrue(publisherList.size() > 0);

    }


}
