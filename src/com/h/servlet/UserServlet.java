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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String type = req.getParameter("type");
        try {
            switch (type) {
                case "1": // 保存
                    User user = new User();
                    user.setUserId(req.getParameter("userId"));
                    user.setUserName(req.getParameter("userName"));
                    user.setUserPassword(req.getParameter("userPassword"));
                    String genderStr = req.getParameter("gender");
                    if (genderStr != null) {
                        user.setGender(Integer.valueOf(genderStr));
                    }
                    String roleIdStr = req.getParameter("roleId");
                    if (roleIdStr == null) {
                        user.setRoleId(2);
                    } else {
                        user.setRoleId(Integer.valueOf(roleIdStr));
                    }
                    userService.addUser(user);
                    req.getRequestDispatcher("/userServlet?type=0").forward(req, resp);
                    break;
                case "2": // 编辑
                    break;
                case "3": // 删除
                    break;
                case "4": // 验证
                    String userId = req.getParameter("userId");
                    User user1 = userService.queryUserById(userId);
                    if (user1 == null) {
                        resp.getWriter().print("1");
                    } else {
                        resp.getWriter().print("2");
                    }
                    break;
                default: // 查询
                    List<User> list = userService.queryUsers();
                    req.setAttribute("userList", list);
                    req.getRequestDispatcher("/pages/user/userList.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
