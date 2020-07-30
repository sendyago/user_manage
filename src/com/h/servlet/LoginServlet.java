package com.h.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("name:" + username);
        System.out.println("password:" + password);
        // 根据用户名查询数据库，如果没有结果，说明用户名不存
        // 如果查询到了，再对比密码，密码不对，返回错误提示

        // admin， 666666
        if ("admin".equals(username) && "666666".equals(password)) {
            // 密码正确，跳转到系统主页
            req.setAttribute("username", username);
            req.getRequestDispatcher("/userServlet").forward(req, resp);
        } else {
            // 用户名或密码错误
            req.setAttribute("username", username);
            req.setAttribute("error", "用户名或密码错误，请重新输入！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
//            resp.sendRedirect("/login.jsp");
        }
    }
}
