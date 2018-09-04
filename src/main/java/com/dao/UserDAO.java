package com.dao;


import java.util.List;

import com.model.user.User;


public interface UserDAO {
    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();

}