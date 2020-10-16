package com.h.servlet;

import com.h.service.MenuService;
import com.h.service.UserService;
import com.h.service.impl.MenuServiceImpl;
import com.h.service.impl.UserServiceImpl;
import com.h.vo.Menu;
import com.h.vo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginServlet extends HttpServlet {

    UserService userService = null;
    MenuService menuService = null;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
        menuService = new MenuServiceImpl();
    }

    @Override
    public void destroy() {
        userService = null;
        menuService = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("name:" + username);
        System.out.println("password:" + password);
        // 根据用户名查询数据库，如果没有结果，说明用户名不存
        // 如果查询到了，再对比密码，密码不对，返回错误提示
        try {

            User user = userService.queryUserById(username);
            // admin， 666666
            if (user != null && user.getUserPassword().equals(password)) {
                // 密码正确，跳转到系统主页
                List<Menu> menus = menuService.queryMenusBy(user.getRoleId()+"");
                req.getSession().setAttribute("menus", menus);
                req.getSession().setAttribute("userId", user.getUserId());
                req.getSession().setAttribute("userName", user.getUserName());
                String menuPath = "index.jsp";
                for (Menu menu: menus) {
                    if (menu.getCheckId() != null) {
                        menuPath = menu.getMenuPath();
                        break;
                    }
                }
                req.getRequestDispatcher(menuPath).forward(req, resp);
            } else {
                // 用户名或密码错误
                req.setAttribute("username", username);
                req.setAttribute("error", "用户名或密码错误，请重新输入！");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
    //            resp.sendRedirect("/login.jsp");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
