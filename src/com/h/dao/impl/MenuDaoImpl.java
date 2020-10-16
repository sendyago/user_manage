package com.h.dao.impl;

import com.h.dao.MenuDao;
import com.h.db.MysqlDB;
import com.h.vo.Menu;
import com.h.vo.RoleMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {
    @Override
    public List<Menu> queryMenusBy(Menu menu, int pageNum, int lineNum) throws SQLException {
        int limit_x = (pageNum - 1) * lineNum;
        int limit_y = lineNum;
        StringBuffer sql = new StringBuffer("select m.* from menuInfo m ");
        sql.append(" where 1 = 1 ");
        if (menu.getMenuName() != null && !"".equals(menu.getMenuName())) {
            sql.append(" and m.menuName like '%").append(menu.getMenuName()).append("%' ");
        }
        sql.append(" order by m.menuId ");
        if (lineNum > 0) {
            sql.append(" limit ").append(limit_x).append(",").append(limit_y);
        }
        System.out.println(sql.toString());
        List<Menu> list = new ArrayList<>();
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                Menu m = new Menu();
                m.setMenuId(rs.getInt("menuId"));
                m.setMenuName(rs.getString("menuName"));
                m.setMenuPath(rs.getString("menuPath"));
                list.add(m);
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
    public int queryMenuCountBy(Menu menu) throws SQLException {
        StringBuffer sql = new StringBuffer("select count(*) as menuCount from menuInfo m ");
        sql.append(" where 1 = 1");
        if (menu.getMenuName() != null && !"".equals(menu.getMenuName())) {
            sql.append(" and m.menuName like '%").append(menu.getMenuName()).append("%' ");
        }
        System.out.println(sql.toString());
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        int menuCount = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                menuCount = rs.getInt("menuCount");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return menuCount;
    }

    @Override
    public Menu queryMenuBy(String menuId) throws SQLException {
        String sql = "select m.* from menuInfo m where m.menuId="+menuId;
        Menu menu = null;
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                menu = new Menu();
                menu.setMenuId(rs.getInt("menuId"));
                menu.setMenuName(rs.getString("menuName"));
                menu.setMenuPath(rs.getString("menuPath"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return menu;
    }

    @Override
    public void add(Menu menu) throws SQLException {
        StringBuffer sql = new StringBuffer("insert into menuInfo(menuName, menuPath) values(");
        sql.append("'" + menu.getMenuName() + "'");
        sql.append(",'" + menu.getMenuPath() + "'");
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
    public void edit(Menu menu) throws SQLException {
        StringBuffer sql = new StringBuffer("update menuInfo set ");
        sql.append(" menuName='" + menu.getMenuName() + "' ");
        sql.append(" ,menuPath='" + menu.getMenuPath() + "' ");
        sql.append(" where menuId = " + menu.getMenuId());
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
    public void deleteMenuBy(String menuId) throws SQLException {
        StringBuffer sql = new StringBuffer("delete from menuInfo ");
        sql.append(" where menuId = " + menuId);
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
    public List<Menu> queryMenusBy(String roleId) throws SQLException {
        StringBuffer sql = new StringBuffer(" select m.*, rm.menuId checkId from menuInfo m left join roleMenuInfo rm on rm.menuId = m.menuId and rm.roleId=");
        sql.append(roleId)
                .append(" order by m.menuId");
        System.out.println(sql.toString());
        List<Menu> list = new ArrayList<>();
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                Menu m = new Menu();
                m.setMenuId(rs.getInt("menuId"));
                m.setMenuName(rs.getString("menuName"));
                m.setMenuPath(rs.getString("menuPath"));
                Object obj = rs.getObject("checkId");
                if (obj != null) {
                    m.setCheckId((Integer) obj);
                }
                list.add(m);
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
    public void save(RoleMenu roleMenu) throws SQLException {
        StringBuffer sql = new StringBuffer(" insert into roleMenuInfo(roleId, menuId) values(");
        sql.append(roleMenu.getRoleId())
                .append(",")
                .append(roleMenu.getMenuId())
                .append(")");
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
    public void deleteRoleMenuBy(String roleId) throws SQLException {
        StringBuffer sql = new StringBuffer(" delete from roleMenuInfo where roleId=");
        sql.append(roleId);
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
