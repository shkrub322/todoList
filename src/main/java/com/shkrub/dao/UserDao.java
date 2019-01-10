package com.shkrub.dao;

import com.shkrub.entities.Users;

public interface UserDao {
    void persist(Users user);
    Users findById(String username);
}
