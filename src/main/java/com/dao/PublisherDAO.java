package com.dao;

import com.model.Book;
import com.model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class PublisherDAO {


    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Object loadObject(Class clazz, Serializable id) {
        return getCurrentSession().load(clazz, id);
    }

    public boolean saveOrUpdateObject(Object object) {
        getCurrentSession().save(object);
        return true;
    }

    public boolean removeObject(Publisher publisher) {
        getCurrentSession().remove(publisher);
        return true;
    }

    public List<Publisher> loadPublishers() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Publisher> criteriaQuery = criteriaBuilder.createQuery(Publisher.class);
        Root<Publisher> root = criteriaQuery.from(Publisher.class);
        criteriaQuery.select(root);
        Query<Publisher> query = currentSession.createQuery(criteriaQuery);
        List<Publisher> publisherList = query.getResultList();
        return publisherList;
    }

    public Publisher loadPublisherById(int publisherId) {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Publisher> criteriaQuery = criteriaBuilder.createQuery(Publisher.class);
        Root<Publisher> root = criteriaQuery.from(Publisher.class);

        Predicate predicateURL = criteriaBuilder.equal(root.get("publisher_id"), publisherId);
        criteriaQuery.select(root).where(predicateURL);
        criteriaQuery.distinct(true);

        Query<Publisher> query = currentSession.createQuery(criteriaQuery);
        Publisher publisher = query.getSingleResult();
        return publisher;


    }


}
