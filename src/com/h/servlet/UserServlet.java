package com.h.servlet;

import com.h.dao.UserDao;
import com.h.service.UserService;
import com.h.service.impl.UserServiceImpl;
import com.h.vo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        try {
            List<User> list = userService.queryUsers();
            req.setAttribute("userList", list);
            req.getRequestDispatcher("/pages/user/userList.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
