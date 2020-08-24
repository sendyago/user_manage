package com.h.dao.impl;

import com.h.dao.RoleDao;
import com.h.db.MysqlDB;
import com.h.vo.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    @Override
    public List<Role> queryRolesBy(Role role, int pageNum, int lineNum) throws SQLException {
        int limit_x = (pageNum - 1) * lineNum;
        int limit_y = lineNum;
        StringBuffer sql = new StringBuffer("select r.* from roleInfo r ");
        sql.append(" where 1 = 1 ");
        if (role.getRoleName() != null && !"".equals(role.getRoleName())) {
            sql.append(" and r.roleName like '%").append(role.getRoleName()).append("%' ");
        }
        sql.append(" order by r.roleName ");
        if (lineNum > 0) {
            sql.append(" limit ").append(limit_x).append(",").append(limit_y);
        }
        System.out.println(sql.toString());
        List<Role> list = new ArrayList<>();
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                Role r = new Role();
                r.setRoleId(rs.getInt("roleId"));
                r.setRoleName(rs.getString("roleName"));
                list.add(r);
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
    public int queryRoleCountBy(Role role) throws SQLException {
        StringBuffer sql = new StringBuffer("select count(*) as roleCount from roleInfo r ");
        sql.append(" where 1 = 1");
        if (role.getRoleName() != null && !"".equals(role.getRoleName())) {
            sql.append(" and r.roleName like '%").append(role.getRoleName()).append("%' ");
        }
        System.out.println(sql.toString());
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        int roleCount = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                roleCount = rs.getInt("roleCount");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return roleCount;
    }

    @Override
    public Role queryRoleBy(String roleId) throws SQLException {
        String sql = "select r.* from roleInfo r where r.roleId="+roleId;
        Role role = null;
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                role = new Role();
                role.setRoleId(rs.getInt("roleId"));
                role.setRoleName(rs.getString("roleName"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return role;
    }

    @Override
    public void add(Role role) throws SQLException {
        StringBuffer sql = new StringBuffer("insert into roleInfo(roleName) values(");
        sql.append("'" + role.getRoleName() + "'");
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
    public void edit(Role role) throws SQLException {
        StringBuffer sql = new StringBuffer("update roleInfo set ");
        sql.append(" roleName='" + role.getRoleName() + "' ");
        sql.append(" where roleId = " + role.getRoleId());
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
    public void deleteRoleBy(String roleId) throws SQLException {
        StringBuffer sql = new StringBuffer("delete from roleInfo ");
        sql.append(" where roleId = " + roleId);
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
