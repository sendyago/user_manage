package com.h.service;

import com.h.vo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> queryUsers(User user, int pageNum, int lineNum) throws SQLException;

    int queryUserCount(User u) throws SQLException;

    User queryUserById(String userId) throws SQLException;

    void addUser(User user) throws SQLException;

    void editUser(User user) throws SQLException;

    void deleteUser(String userId) throws SQLException;

}
