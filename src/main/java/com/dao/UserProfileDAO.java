package com.dao;

import java.util.List;

import com.model.user.UserProfile;


public interface UserProfileDAO {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}