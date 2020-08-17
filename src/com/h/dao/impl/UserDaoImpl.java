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
    public List<User> queryUsers(User u, int pageNum, int lineNum) throws SQLException {
        int limit_x = (pageNum - 1) * lineNum;
        int limit_y = lineNum;
        StringBuffer sql = new StringBuffer("select u.*, r.roleName from userInfo u left join roleInfo r on r.roleId = u.roleId");
        sql.append(" where 1 = 1 ");
        if (u.getUserId() != null && !"".equals(u.getUserId())) {
            sql.append(" and u.userId like '%" + u.getUserId() + "%' ");
        }
        if (u.getUserName() != null && !"".equals(u.getUserName())) {
            sql.append(" and u.userName like '%" + u.getUserName() + "%' ");
        }
        if (u.getGender() != 0) {
            sql.append(" and u.gender =" + u.getGender());
        }
        sql.append(" order by u.userName ");
        sql.append(" limit " + limit_x + "," + limit_y);
        System.out.println(sql.toString());
        List<User> list = new ArrayList<>();
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
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
    public int queryUserCount(User u) throws SQLException {
        StringBuffer sql = new StringBuffer("select count(*) as userCount from userInfo u ");
        sql.append(" where 1 = 1");
        if (u.getUserId() != null && !"".equals(u.getUserId())) {
            sql.append(" and u.userId like '%" + u.getUserId() + "%' ");
        }
        if (u.getUserName() != null && !"".equals(u.getUserName())) {
            sql.append(" and u.userName like '%" + u.getUserName() + "%' ");
        }
        if (u.getGender() != 0) {
            sql.append(" and u.gender =" + u.getGender());
        }
        System.out.println(sql.toString());
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        int userCount = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                userCount = rs.getInt("userCount");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return userCount;
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

    @Override
    public void editUser(User user) throws SQLException {
        StringBuffer sql = new StringBuffer("update userInfo set ");
        sql.append(" userName='" + user.getUserName() + "',");
        sql.append(" userPassword='" + user.getUserPassword() + "',");
        sql.append(" gender=" + user.getGender() + ",");
        sql.append(" roleId=" + user.getRoleId());
        sql.append(" where userId='" + user.getUserId() + "'");
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

    public void deleteUser(String userId) throws SQLException {
        String sql = "delete from userInfo where userId='"+userId+"'";
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
    }
}
