package com.dao;

import com.model.library.Book;
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
public class BookDAO {

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

    public boolean removeObject(Book book) {
        getCurrentSession().remove(book);
        return true;
    }

    public List<Book> loadBooks() {
        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root);
        Query<Book> query = currentSession.createQuery(criteriaQuery);
        List<Book> bookList = query.getResultList();
        return bookList;
    }

    public Book loadBookById(Long bookId){

        Session currentSession = getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        Predicate predicateURL = criteriaBuilder.equal(root.get("bookId"), bookId);
        criteriaQuery.select(root).where(predicateURL);
        criteriaQuery.distinct(true);

        Query<Book> query = currentSession.createQuery(criteriaQuery);
        Book book = query.getSingleResult();
        return book;



    }


}
