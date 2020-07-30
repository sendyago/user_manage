package com.h.service.impl;

import com.h.dao.UserDao;
import com.h.dao.impl.UserDaoImpl;
import com.h.service.UserService;
import com.h.vo.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> queryUsers() throws SQLException {
        UserDao userDao = new UserDaoImpl();
        return userDao.queryUsers();
    }
}
