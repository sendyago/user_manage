package com.h.service.impl;

import com.h.dao.UserDao;
import com.h.dao.impl.UserDaoImpl;
import com.h.service.UserService;
import com.h.vo.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> queryUsers(User user, int pageNum, int lineNum) throws SQLException {
        int limit_x = (pageNum - 1) * lineNum;
        int limit_y = lineNum;
        return userDao.queryUsers(user, limit_x, limit_y);
    }

    @Override
    public int queryUserCount(User u) throws SQLException {
        return userDao.queryUserCount(u);
    }

    @Override
    public User queryUserById(String userId) throws SQLException {
        return userDao.queryUserById(userId);
    }

    @Override
    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    @Override
    public void editUser(User user) throws SQLException {
        userDao.editUser(user);
    }

    @Override
    public void deleteUser(String userId) throws SQLException {
        userDao.deleteUser(userId);
    }
}
