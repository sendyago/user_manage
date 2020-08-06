package com.h.dao.impl;

import com.h.dao.UserDao;
import com.h.db.MysqlDB;
import com.h.vo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> queryUsers() throws SQLException {
        String sql = "select u.*, r.roleName from userInfo u left join roleInfo r on r.roleId = u.roleId";
        List<User> list = new ArrayList<>();
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("userId"));
                user.setUserName(rs.getString("userName"));
                user.setGender(rs.getInt("gender"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setRoleId(rs.getInt("roleId"));
                user.setRoleName(rs.getString("roleName"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return list;
    }

    @Override
    public User queryUserById(String userId) throws SQLException {
        String sql = "select u.* from userInfo u where u.userId='"+userId+"'";
        User user = null;
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getString("userId"));
                user.setUserName(rs.getString("userName"));
                user.setGender(rs.getInt("gender"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setRoleId(rs.getInt("roleId"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return user;
    }

    @Override
    public void addUser(User user) throws SQLException {
        StringBuffer sql = new StringBuffer("insert into userInfo(userId,userName,userPassword,gender,roleId) values(");
        sql.append("'" + user.getUserId() + "',");
        sql.append("'" + user.getUserName() + "',");
        sql.append("'" + user.getUserPassword() + "',");
        sql.append( user.getGender() + ",");
        sql.append( user.getRoleId() );
        sql.append(")");
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql.toString());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
    }
}
