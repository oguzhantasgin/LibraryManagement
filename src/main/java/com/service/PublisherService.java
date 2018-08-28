package com.service;


import com.dao.PublisherDAO;
import com.model.Publisher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class  PublisherService {

    @Autowired
    private PublisherDAO publisherDAO;

    public JSONObject loadPublishers() {

        List<Publisher> publisherList = publisherDAO.loadPublishers();
        JsonConfig jsonConfig = new JsonConfig();
        JSONArray jsonArray = JSONArray.fromObject(publisherList, jsonConfig);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        jsonObject.put("success", true);
        jsonObject.put("totalCount", jsonArray.size());
        return jsonObject;

    }

    @Transactional
    public JSONObject saveOrUpdatePublisher(JSONObject jsonObject) throws ParseException {

        Publisher publisher = null;
        Integer publisher_id = null;


        if (jsonObject.has("publisherId") && jsonObject.get("publisherId") != null && !jsonObject.getString("publisherId").equals(""))
            publisher_id = jsonObject.getInt("publisherId");

        if (publisher_id != null) {
            publisher = (Publisher) publisherDAO.loadObject(Publisher.class, publisher_id);
        } else {
            publisher = new Publisher();
        }

        String publisherName = jsonObject.getString("publisherName");
        String publisherAddress = jsonObject.getString("publisherAddress");


        publisher.setPublisherName(publisherName);
        publisher.setPublisherAddress(publisherAddress);


        boolean success = publisherDAO.saveOrUpdateObject(publisher);

        JSONObject jsonReturn = new JSONObject();
        jsonReturn.put("success", success);
        return jsonReturn;

    }

    @Transactional
    public JSONObject deletePublisher(Integer publisherId) throws ParseException {

        Publisher publisher = (Publisher) publisherDAO.loadObject(Publisher.class, publisherId);
        boolean success = publisherDAO.removeObject(publisher);
        JSONObject jsonReturn = new JSONObject();
        jsonReturn.put("success", success);
        return jsonReturn;

    }

}
