package com.h.servlet;

import com.h.dao.RoleDao;
import com.h.dao.UserDao;
import com.h.dao.impl.RoleDaoImpl;
import com.h.dao.impl.UserDaoImpl;
import com.h.vo.Role;
import com.h.vo.User;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
//        addTestUsers();

        addTestRoles();
    }


    public static void addTestUsers() {
        UserDao userDao = new UserDaoImpl();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setUserId("user"+(i+10));
            user.setUserName("测试用户"+(i+10));
            user.setGender(i%2);
            user.setRoleId(2);
            user.setUserPassword("111111");
            try {
                userDao.addUser(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void addTestRoles() {
        RoleDao roleDao = new RoleDaoImpl();
        for (int i = 0; i < 30; i++) {
            Role role = new Role();
            role.setRoleName("普通用户"+(10+i));
            try {
                roleDao.add(role);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
