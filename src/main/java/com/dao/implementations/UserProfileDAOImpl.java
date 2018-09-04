package com.dao.implementations;


import java.util.List;

import com.dao.AbstractDAO;
import com.dao.UserProfileDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.model.user.UserProfile;


@Repository("userProfileDao")
public class UserProfileDAOImpl extends AbstractDAO<Integer, UserProfile> implements UserProfileDAO {

    public UserProfile findById(int id) {
        return getByKey(id);
    }

    public UserProfile findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserProfile) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<UserProfile> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<UserProfile>) crit.list();
    }

}